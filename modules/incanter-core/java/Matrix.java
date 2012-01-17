/*
* Matrix.java -- Matrix class for Clojure that extends DenseColumnDoubleMatrix2D
* in the CERN Colt Library
*
* by David Edgar Liebke http://incanter.org
* March 11, 2009
*
* Copyright (c) David Edgar Liebke, 2009. All rights reserved.  The use
* and distribution terms for this software are covered by the Eclipse
* Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
* which can be found in the file epl-v10.htincanter.at the root of this
* distribution.  By using this software in any fashion, you are
* agreeing to be bound by the terms of this license.  You must not
* remove this notice, or any other, from this software.
*/

/*
CHANGE LOG
March 11, 2009: First version
*/

package incanter;

import cern.colt.matrix.tdouble.impl.DenseColumnDoubleMatrix2D;
import cern.colt.matrix.tdouble.DoubleMatrix2D;
import cern.colt.function.tdouble.DoubleDoubleFunction;
import cern.colt.function.tdouble.DoubleFunction;

import clojure.lang.IObj;
import clojure.lang.ISeq;
import clojure.lang.Counted;
import clojure.lang.IPersistentCollection;
import clojure.lang.IPersistentMap;
import clojure.lang.Seqable;
import clojure.lang.Sequential;

public class Matrix extends DenseColumnDoubleMatrix2D implements Sequential, ISeq, Counted, IObj {

        public boolean oneDimensional = false;
        IPersistentMap meta;

        /**************************************
         * MATRIX CONSTRUCTORS
        **************************************/

        public Matrix(int nrow, int ncol) {
                this(nrow, ncol, 0);
                if(this.rows == 1 || this.columns == 1)
                        this.oneDimensional = true;
        }

        public Matrix(int nrow, int ncol, Number initValue) {
                super(nrow, ncol);
                this.meta = null;
                for(int i = 0; i < nrow; i++)
                        for(int j = 0; j < ncol; j++)
                                this.set(i, j, initValue.doubleValue());
                if(this.rows == 1 || this.columns == 1)
                        this.oneDimensional = true;
        }

        public Matrix(double[] data) {
                this(data, 1);
        }

        public Matrix(double[] data, int ncol) {
                super(data.length/ncol, ncol);
                this.meta = null;
                if(this.rows == 1 || this.columns == 1)
                        this.oneDimensional = true;
                for(int i = 0; i < this.rows; i++)
                        for(int j = 0; j < this.columns; j++)
                                this.set(i, j, data[i*ncol+j]);
        }

        public Matrix(double[][] data) {
                super(data);
                this.meta = null;
                if(this.rows == 1 || this.columns == 1)
                        this.oneDimensional = true;
        }

        public Matrix(IPersistentMap meta, double[][] data) {
                this(data);
                this.meta = meta;
        }

        public Matrix(DoubleMatrix2D mat) {
                this(mat.rows(), mat.columns());
                this.assign(mat);
        }

        public Matrix(int rows, int columns, double[] elements, boolean oneDimensional) {
                super(rows, columns, elements, 0, 0, columns, 1, false);
                this.meta = null;
                this.oneDimensional = oneDimensional;
        }


        public Matrix(Seqable coll, int rows, int columns) {
                super(rows, columns);
                this.meta = null;
                ISeq seq = coll.seq();
                for(int i = 0; i < (rows); i++) {
                        for(int j = 0; j < (columns); j++) {
                                this.set(i, j, ((Number)(seq.first())).doubleValue());
                                seq = seq.next();
                        }
                }
        }

        /**************************************
         * MATRIX METHODS
        **************************************/
        public Matrix viewSelection(int[] rows, int[] columns) {
                Matrix mat = new Matrix(super.viewSelection(rows, columns));
                if(rows.length == 1 || columns.length == 1)
                        mat.oneDimensional = true;

                return(mat);
        }

        public Matrix like(int rows, int columns) {
                return new Matrix(rows, columns);
        }

        /**************************************
         * ISeq METHODS
        **************************************/

        public Object first() {
                if(this.rows == 0 || this.columns == 0) return(null);

                if(this.oneDimensional && (this.columns == 1 || this.rows == 1))
                        return(this.get(0, 0));
                else {
                        double[] subset = new double[this.columns];
                        int idx=0;
                        for(int i=0; i < subset.length; i++) {
                                subset[i] = getQuick(0, idx++);
                        }
                        return new Matrix(1, this.columns, subset, true);
                }
        }


        public ISeq next() {
                if(this.rows == 0 || this.columns == 0)
                        return(null);
                else if(!this.oneDimensional && this.rows == 1) {
                        return(null);
                }
                else if(this.oneDimensional && (this.columns == 1 || this.rows == 1)) {
                        double[] subset = new double[(this.rows*this.columns)-1];
                        int idx=0;
                        for(int i=1; i < this.elements.length; i++)
                                subset[idx++] = this.elements[i];
                        if(this.rows > 1)
                                return new Matrix(this.rows-1, this.columns, subset, true);
                        else if(this.columns > 1)
                                return new Matrix(this.rows, this.columns-1, subset, true);
                        else
                                return null;
                }
                else {
                        double[] subset = new double[(this.rows-1)*this.columns];
                        int idx = 0;
                        for(int i=1; i < this.rows; i++) {
                                for(int j=0; j < this.columns; j++) {
                                        subset[idx++] = getQuick(i, j);
                                }
                        }
                        return new Matrix(this.rows-1, this.columns, subset, false);
                }
        }

        public ISeq more() {
                ISeq result = this.next();
                if(result != null)
                        return result;
                else
                        return new Matrix(0, 0, 0);
        }

        public Matrix cons(Object o) {

                if(o instanceof Matrix) {
                        Matrix m = (Matrix)o;
                        double[][] newData = new double[this.rows + m.rows][this.columns];
                        for(int i = 0; i < (this.rows); i++)
                                for(int j = 0; j < (this.columns); j++)
                                        newData[i][j] = this.getQuick(i, j);

                        for(int i = 0; i < m.rows; i++)
                                for(int j = 0; j < (this.columns); j++)
                                        newData[this.rows + i][j] = m.getQuick(i, j);

                        return(new Matrix(newData));
                }
                else if(o instanceof Seqable) {
                        ISeq v = ((Seqable)o).seq();
                        double[][] newData = new double[this.rows + 1][this.columns];
                        for(int i = 0; i < (this.rows); i++)
                                for(int j = 0; j < (this.columns); j++)
                                        newData[i][j] = this.getQuick(i, j);
                        ISeq restObj = v;
                        int cols = 0;
                        while(cols < this.columns) {
                                newData[this.rows][cols] = ((Number)(restObj.first())).doubleValue();
                                restObj = restObj.next();
                                cols++;
                        }

                        return(new Matrix(newData));
                }
                else
                        return(null);
        }

        public int count() {
                if(this.oneDimensional)
                        return this.elements.length;
                else
                        return this.rows;
        }

        public Matrix seq() {
                if(this.elements.length > 0)
                        return this;
                else
                        return null;
        }

        public IPersistentCollection empty() {
                return(new Matrix(0, 0));
        }

        public boolean equiv(Object o) {
                return equals(o);

        }

        public String toString() {
                StringBuffer buf = new StringBuffer();
                for(int i = 0; i < this.rows; i++) {
                        for(int j = 0; j < this.columns; j++) {
                                buf.append(this.get(i, j));
                                buf.append("   ");
                        }
                        buf.append("\n");
                }
                return(buf.toString());
        }

        /**************************************
         * IOBJ METHODS
        **************************************/
        public IPersistentMap meta(){
                return this.meta;
        }

        public Matrix withMeta(IPersistentMap meta) {
                return new Matrix(meta, super.toArray());
        }
}
