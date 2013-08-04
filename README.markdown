sbt-man
=======

sbt-man is an sbt plugin for looking up scaladoc.

## how to setup

For sbt 0.12, add the following to your `~/.sbt/plugins/build.sbt`:

    addSbtPlugin("com.eed3si9n" % "sbt-man" % "0.1.0")

For sbt 0.13, add the following to your `~/.sbt/0.13/plugins/man.sbt`:

    addSbtPlugin("com.eed3si9n" % "sbt-man" % "0.1.1")
    
## how to use

The above automatically adds `man` command.
It searches the scaladoc of Scala standard library and Scalaz using [Scalex][1], and displays the first result.

    > man Traversable /:
    [man] scala.collection.Traversable
    [man] def /:[B](z: B)(op: (B ⇒ A ⇒ B)): B
    [man] Applies a binary operator to a start value and all elements of this
    collection, going left to right. Note: /: is alternate syntax for foldLeft;
    z /: xs is the same as xs foldLeft z. Note: will not terminate for infinite-
    sized collections. Note: might return different results for different runs,
    unless the underlying collection type is ordered. or the operator is
    associative and commutative. 

## license

Anything I copied from [Scalex-CLI][2] is licensed using Apache v2 license.
Everything else is licensed using MIT License.

  [1]: http://scalex.org/
  [2]: https://github.com/jonifreeman/Scalex-CLI
