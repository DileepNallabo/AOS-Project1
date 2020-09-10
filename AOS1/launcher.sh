#!/bin/bash

# Change this to your netid
netid=dxn180025

# Root directory of your project
PROJDIR=/home/013/d/dx/dxn180025/cs6378/project1

# Directory where the config file is located on your local system
CONFIGLOCAL=$HOME/AOS1/config.txt

# Directory your java classes are in
BINDIR=$PROJDIR/bin

# Your main project class
PROG=Main

n=0

cat $CONFIGLOCAL | sed -e "s/#.*//" | sed -e "/^\s*$/d" |
(
    read i
    echo $i
    while [[ $n -lt $i ]]
    do
    	read line
    	p=$( echo $line | awk '{ print $1 }' )
        host=$( echo $line | awk '{ print $2 }' )
	
	gnome-terminal -e "ssh -o UserKnownHostsFile=/dev/null -o StrictHostKeyChecking=no $netid@$host java -cp $BINDIR $PROG $p; exec bash" &

        n=$(( n + 1 ))
    done
)
