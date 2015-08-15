#!/bin/bash
MAX_TUPLE=12;

# generate tuple files
php ./tuple_interface_template.php $MAX_TUPLE > ../src/net/sourcedestination/funcles/tuple/Tuple.java

# generate the abstract interface for tuples and its helper methods
for i in `seq 2 $MAX_TUPLE`; do
	php ./tuple_template.php $i > ../src/net/sourcedestination/funcles/tuple/Tuple$i.java
done 

# generate same-type tuples
# TODO

# generate predicates
for i in `seq 2 $MAX_TUPLE`; do
	php ./predicate_template.php $i > ../src/net/sourcedestination/funcles/predicate/Predicate$i.java
done 

# generate functions
for i in `seq 2 $MAX_TUPLE`; do
	php ./function_template.php $i > ../src/net/sourcedestination/funcles/function/Function$i.java
done 

# generate consumers
for i in `seq 2 $MAX_TUPLE`; do
	php ./consumer_template.php $i > ../src/net/sourcedestination/funcles/consumer/Consumer$i.java
done 

# generate the helper methods in the Funcles class
php ./funcles_template.php $MAX_TUPLE > ../src/net/sourcedestination/funcles/Funcles.java
