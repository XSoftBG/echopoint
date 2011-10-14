#!/bin/sh

# Script to create a tagged release in subversion

if [ $# -ne 1 ]
then
  echo "Usage: $0 <version>"
  exit 1
fi

echo "Creating version: tags/$1"
svn copy https://echopoint.dev.java.net/svn/echopoint/trunk \
  https://echopoint.dev.java.net/svn/echopoint/tags/$1 \
  -m "Created release: $1 of library"

echo "Deleting existing tags/current"
svn delete https://echopoint.dev.java.net/svn/echopoint/tags/current \
  -m "Removing old current directory to create release: $1 of library"

echo "Creating new tags/current"
svn copy https://echopoint.dev.java.net/svn/echopoint/trunk \
  https://echopoint.dev.java.net/svn/echopoint/tags/current \
  -m "Created release: $1 of library"
