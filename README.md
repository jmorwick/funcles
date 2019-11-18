# Funcles

Funcles is a tuple and higher arity functional interface library for Java 8+. Funcles tuples and functional interfaces are type-safe, minimally "wordy", and built on top of existing Java 8 collections and functional interfaces where appropriate. These functional interfaces can be used as types for referencing methods with more than one parameter. 

## Current Version

... is 2.0. Further releases will follow changes in future releases of the JDK where necessary, or possibly add features and currently unsupported functional interfaces in the JDK. 

## How to include funcles in your project

Download the jar or simply [add the maven dependency](https://search.maven.org/#artifactdetails%7Cnet.sourcedestination%7Cfuncles%7C2.0%7Cjar). 

## Why a Java tuples library? 

Some argue that, in Java, there is never a need for tuple classes at all, since whatever a tuple may represent is simply an obsfucated "value class". Thus, a tuple abstraction is typically given very limited support or left out entirely of popular libraries like guava, apache commons, or the JDK. I developed this library for those who either disagree with this assessment of tuples entirely, or others (like me) who mostly agree but also feel there's a middle ground -- that sometimes using tuples obsfucates your code and that sometimes creating a value class for a very simple role may add more confusion and brittleness to your code than it's worth, and that good developers should be able to make a wise choice in these situations. 

## Why *another* Java tuples library?

There are many great tuple implementations out there and one may question why I felt the need to develop another. In [issue #3](https://github.com/jmorwick/funcles/issues/3) I summarize the features of many of these. If all I cared to do was use tuples, I would probably be using one of these. Even still, it's quite simple to toss a type-safe tuple implementation in to a project without using a third party library. 

The main reason I developed this library was to extend the new functional interfaces in Java 8 to go beyond unary functions. Java 8 functional interfaces, such as `java.util.function.Function`, have one or zero type arguments for inputs and one or zero type arguments for outputs. This requires the programmer to develop a special class to encompass input to any particular function they want to use these interfaces for. There is an argument for using value classes in place of using multiple parameters, but suffice it to say: many developers still want to write functions with multiple parameters because it's simple and appropriate. Creating a class for each parameter list would make a codebase bulky and brittle. 

This has been partially addressed with the `java.util.function.BiFunction` interface, but I didn't feel it went far enough (perhaps I would want three, four, etc.. parameters), and was not consistent enough with other interfaces in the library (you couldn't abstract over functions of multiple arities). To me, it made more sense to simply use a tuple as the parameter type. 

## Is there a different class for each arity of tuple?

Yes. Java doesn't have macros capable of getting around this issue in a clean and type safe way. This creates obvious problems for maintatining a tuple library in Java, since making a change to one tuple class would require updates to each of the classes for the other arities. However, the tuples and interfaces in the funcles library were developed with templates so that the arity can be easily and arbitrarily increased if a developer so desires. The main downside, from my perspective, is the need to either use a wild-card in your import statements or use multiple imports for each arity of tuple/interface. 

## Example tuple usage

Here is a function that uses 2-tuples (essentially a pair class) as an intermediary to implementing argmax. The 2-tuple is used entirely internally to this function and even an inner-class would be overkill for the job:

```
  public static <F,T extends Comparable<T>> F argmax(Function<F,T> f, Stream<F> inputs) {
   	return inputs
            .map(x -> makeTuple(x, f.apply(x)))  // map to pairs of input/output for function
            .max(comparing(Tuple2::_2))  // find the tuple with maximum output
            .get()._1();                 // retrieve the input for that maximum output
   }
```

## Example functional interface usage

The funcles `Function2<P1, P2, R>` interface extends the JDK's `Function<Tuple2<P1,P2>,R>` but includes a default method which unpacks the tuple. This means you can assign method references or lambdas with multiple parameters directly to it (no need to even mention a tuple type) and still use it in contexts where you need a `java.util.function.Function`. Here is a simple example:

```
Function2<Integer,Integer,Boolean> greater = (x,y) -> x > y;
Function<Tuple2<Integer,Integer>,Boolean> greater2 = greater;
```

This means `greater` could be used with the `argmax` example above without modification:

```
ArrayList<Tuple2<Integer,Integer>> ls = new ArrayList<>();
ls.add(makeTuple(1, 2));
ls.add(makeTuple(5, 1));
ls.add(makeTuple(3, 4));
System.out.println(argmax(greater, ls));
```
