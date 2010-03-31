; generates a jar file in the pwd named cl.jar
; cl.jar contians a class file that contains a class named x.cl
; x.cl is a ClassLoader which is extended to have a method 'add'
; 'add' takes a ClassLoader or a URL
; in the case you call 'add' on a URL the URL is wrapped in a URLClassLoader
; x.cl searches the list of 'add'ed ClassLoaders for classes
;
;   idea is to use it for developement, when dynamically adding classes from
; jars could be useful.

 
(import '(clojure.asm ClassWriter Opcodes Label)
	'(java.io File InputStream FileOutputStream BufferedOutputStream)
	'(java.util.jar JarOutputStream JarEntry))

(defn field [cw access name desc signature value]
  (.visitEnd (.visitField cw access name desc signature value)))

(defmacro method
  "classwritter, access bits, name of the class"
  [cw [access name desc signature exceptions] & body]
  `(doto ~cw
       ((fn [x#]
	  (let [w# (.visitMethod x# ~access ~name ~desc ~signature
				 (into-array String ~exceptions))]
	    (.visitCode w#)
	    (ops w# ~@body)
	    (maxs w#)
	    (.visitEnd w#))))))

(defn label [x]
  (Label.))

(declare *THIS*)

(def synthetic-instructions (ref #{}))

(defmacro op
  ([obj I]
     (cond (synthetic-instructions I)
	    `(~I ~obj)
	   (keyword? I)
	    `(op ~obj (label ~I))
	   :else
	     `(.visitInsn ~obj (. Opcodes ~I))))
  ([obj I addr]
     (cond (#{'IFEQ 'GOTO 'IFNE 'IFNULL} I)
	     `(.visitJumpInsn ~obj (. Opcodes ~I) (label ~addr))
	   (= I 'LABEL)
	     `(.visitLabel ~obj (label ~addr))
	     (#{'NEW 'CHECKCAST} I)
	     `(.visitTypeInsn ~obj (. Opcodes ~I) ~addr)
	   (= I 'LDC)
	     `(.visitLdcInsn ~obj ~addr)
	   :else
	     `(.visitVarInsn ~obj (. Opcodes ~I) ~addr)))
  ([obj I desc dims]
     `(.visitMultiANewArrayInsn ~obj ~desc ~dims))
  ([obj I target name sig]
     (cond (#{'PUTFIELD 'GETFIELD 'GETSTATIC 'PUTSTATIC} I) 
	     `(.visitFieldInsn ~obj (. Opcodes ~I) ~target ~name ~sig)	   
	   :else
	     `(.visitMethodInsn ~obj (. Opcodes ~I) ~target ~name ~sig)))
  ([obj I try catch finally exception]
     `(.visitTryCatchBlock ~obj (label ~try) (label ~catch) (label ~finally)
			   ~exception)))

(defmacro maxs
  ([obj] `(maxs ~obj 0 0))
  ([obj x y] `(.visitMaxs ~obj ~x ~y)))

(defmacro ops [obj & os]
  (let [x (map #(if (list? %)
		  (cons 'op (cons (-> % first name .toUpperCase symbol)
				  (rest %)))
		  (list 'op (-> % name .toUpperCase symbol))) os)]
    `(doto ~obj ~@x)))

(defn make-jar [name & files]
  (with-open [out (-> name File. FileOutputStream.
		      BufferedOutputStream. JarOutputStream.)]
    (doseq [f files]
      (let [jentry (JarEntry. (first f))]
	(.putNextEntry out jentry)
	(.write out (second f))))))

(defmacro make-class [name super faces & body]
  `(binding [label (memoize label) *THIS* ~name]
     (doto (ClassWriter. ClassWriter/COMPUTE_FRAMES)
       (.visit Opcodes/V1_5 Opcodes/ACC_PUBLIC
	       ~name nil ~super ~faces)
       ~@body
       .visitEnd
       ((fn [x#]
	  (make-jar (-> ~name (.split "/") last (str ".jar"))
		    [(format "%s.class" ~name) (.toByteArray x#)])))
       #_((fn [x#]
	  (-> "%s.class" (format ~name)
	      java.io.File.
	      java.io.FileOutputStream.
	      (.write (.toByteArray x#))))))))

(defmacro LIST-ADD [obj]
  `(ops ~obj
       ~'dup2
       ~'pop
       ~'swap
       ~'(invokeinterface "java/util/List" "add" "(Ljava/lang/Object;)Z")
       ~'pop))

(dosync (alter synthetic-instructions conj 'LIST-ADD))

(defmacro TO-STRING [obj]
  `(ops ~obj 
	~'(invokevirtual "java/lang/Object" "toString" "()Ljava/lang/String;")))

(dosync (alter synthetic-instructions conj 'TO-STRING))

(defmacro PRINTLN [obj]
  `(ops ~obj
	~'dup
	#_~'(invokevirtual "java/lang/Object" "toString" "()Ljava/lang/String;")
	~'to-string
	~'(getstatic "java/lang/System" "out" "Ljava/io/PrintStream;")
	~'swap
	~'(invokevirtual "java/io/PrintStream" "println"
		       "(Ljava/lang/String;)V")))
(dosync (alter synthetic-instructions conj 'PRINTLN))

(defmacro ARRAY-LIST [obj]
  `(ops ~obj
	~'(new "java/util/ArrayList")
	~'dup
	~'(invokespecial "java/util/ArrayList" "<init>" "()V")))
(dosync (alter synthetic-instructions conj 'ARRAY-LIST))

(defmacro IT-NEXT? [obj]
  `(ops ~obj
	~'dup
	~'(invokeinterface "java/util/Iterator" "hasNext" "()Z")))
(dosync (alter synthetic-instructions conj 'IT-NEXT?))

(defmacro IT-NEXT [obj]
  `(ops ~obj
	~'dup
	~'(invokeinterface "java/util/Iterator" "next" "()Ljava/lang/Object;")))
(dosync (alter synthetic-instructions conj 'IT-NEXT))


(make-class "x/cl" "java/lang/ClassLoader" nil
  (field Opcodes/ACC_PUBLIC "loaders" "Ljava/util/List;" nil nil)
  (method [Opcodes/ACC_PUBLIC
	   "<init>" "(Ljava/lang/ClassLoader;)V" nil nil]
    (ALOAD 0)
    dup
    (ALOAD 1)
    (INVOKESPECIAL "java/lang/ClassLoader"
		   "<init>" "(Ljava/lang/ClassLoader;)V")
    array-list
    (PUTFIELD *THIS* "loaders" "Ljava/util/List;")
    RETURN)
  (method [Opcodes/ACC_PUBLIC "add" "(Ljava/lang/ClassLoader;)V"
	   nil nil]
    (ALOAD 0)
    (GETFIELD *THIS* "loaders" "Ljava/util/List;")
    (ALOAD 1)
    (INVOKEINTERFACE "java/util/List" "add"
		     "(Ljava/lang/Object;)Z")
    RETURN)
  (method [Opcodes/ACC_PUBLIC "add" "(Ljava/net/URL;)V" nil nil]
    iconst_1
					;-- int
    (anewarray "[Ljava/net/URL;" 1)
					;-- [URL 
    dup
					;-- [URL [URL 
    iconst_0
					;-- [URL [URL int 
    (aload 1)
					;-- [URL [URL int URL 
    aastore
					;-- [URL
    (new "java/net/URLClassLoader")
					;-- [URL URLClassLoader
    dup2
					;-- [URL URLClassLoader [URL URLClassLoader
    swap
					;-- [URL URLClassLoader URLClassLoader [URL
    aconst_null 
    (INVOKESPECIAL "java/net/URLClassLoader"
		   "<init>" "([Ljava/net/URL;Ljava/lang/ClassLoader;)V")
					;-- [URL URLClassLoader
    (aload 0)
    swap
    (invokevirtual *THIS* "add"
		   "(Ljava/lang/ClassLoader;)V")
    return)
  (method [Opcodes/ACC_PRIVATE "_"
	   "(Ljava/lang/String;Ljava/lang/ClassLoader;)Ljava/lang/Class;"
	   nil nil]
    (trycatchblock :try :catch :catch "java/lang/ClassNotFoundException")
    (aload 2)
    (aload 1)
    (label :try)
    (invokevirtual "java/lang/ClassLoader" "loadClass"
		   "(Ljava/lang/String;)Ljava/lang/Class;")
    areturn
    (label :catch)
    aconst_null
    areturn)
  (method [Opcodes/ACC_PROTECTED "findClass"
	   "(Ljava/lang/String;)Ljava/lang/Class;" nil nil]
    (aload 0)
					;-- this
    dup
					;-- this this
    (getfield *THIS* "loaders" "Ljava/util/List;")
					;-- this List
    (invokeinterface "java/util/List" "iterator" "()Ljava/util/Iterator;")
					;-- this Iterator
    (label :top)
					;-- "
    it-next?
					;-- this Iterator bool
    (ifeq :bottom)
					;-- this Iterator
    it-next
					;-- this Iterator Object
    (checkcast "java/lang/ClassLoader")
					;-- this Iterator ClassLoader
    (aload 0)
					;-- this Iterator ClassLoader this
    swap
					;-- this Iterator this ClassLoader 
    (aload 1)
					;-- this Iterator this ClassLoader String 
    swap
					;-- this Iterator this String ClassLoader
    (invokevirtual *THIS* "_"
		   "(Ljava/lang/String;Ljava/lang/ClassLoader;)Ljava/lang/Class;")
					;-- this Iterator Class
    dup
					;-- this Iterator Class Class
    (ifnull :bob)
					;-- this Iterator Class 
    areturn
					;-- 
    (label :bottom)
					;-- this Iterator
    aconst_null
					;-- this Iterator null
    ARETURN
					;-- 
    (label :bob)
					;-- this Iterator Class 
    pop
					;-- this Iterator
    (goto :top)))

(println "go forth and...")
(println "java -Djava.system.class.loader=x.cl -cp cl.jar:... ...")
(println "Where X is a URL or a ClassLoader")
(println "(.add (ClassLoader/getSystemClassloader) X)")
