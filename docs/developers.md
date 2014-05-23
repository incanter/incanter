# Incanter developer info

## Leiningen

Incanter contains subprojects in `modules`.  It uses
[lein-sub](https://github.com/kumarshantanu/lein-sub) to manage them;
this replaces the shell scripts (clean, install, package, push, test)
that used to be in `script`.

To run a task such as `clean` on all subprojects, do:

```
$ lein sub clean
```

To run a task on a single subproject:
```
$ lein sub -s modules/incanter-core clean
```

