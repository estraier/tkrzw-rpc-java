# Makefile for sample programs of Tkrzw

#================================================================
# Setting Variables
#================================================================

# Generic settings
SHELL = /bin/sh

# Targets
MYCLASSES = Example1.class Example2.class

# Building binaries
JAVAC = javac
JAVACFLAGS = -cp .:../tkrzw_rpc.jar
JAVARUN = java
JAVARUNFLAGS = -cp .:/usr/share/java/*:../tkrzw_rpc.jar -Djava.library.path=.:..:/usr/local/lib

#================================================================
# Suffix rules
#================================================================

.SUFFIXES :
.SUFFIXES : .java .class

.java.class :
	$(JAVAC) $(JAVACFLAGS) $<

#================================================================
# Actions
#================================================================

all : $(MYCLASSES)

run :
	rm -rf casket*
	$(JAVARUN) $(JAVARUNFLAGS) Example1
	$(JAVARUN) $(JAVARUNFLAGS) Example2

clean :
	rm -rf $(MYCLASSES) *.class casket* *~

.PHONY : all clean

# END OF FILE
