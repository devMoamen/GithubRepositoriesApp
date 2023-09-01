# KryptonicApp

An android app for getting github repositories so with ability to add favorite repository to favorites list 
also user can filter repositorties by last day,last week and last month

This is an Android app that demonstrates the usage of Room for local database storage, Dagger for dependency injection, and Hilt for simplified dependency injection setup.

## Table of Contents

- [Features](#features)
- [Why using these features](#reasons)
- [No inclued in this project](#Not_Inclued)
- [No funcational](#Nonfuncational)
  
## Features
- Local data storage using Room
- Dependency injection with Dagger Hilt
- MVVM architectural pattern
- Retrofit2 network library
- Coroutine and Flow
- LiveData an observers

## Reasons
Using Retrofit, Dagger Hilt, Room, and Coroutines together in an Android app is a common practice to build a robust and efficient application with a modern architecture

- Retrofit: Retrofit is a widely-used HTTP client library for Android,that simplifies the process of making network requests
- Dagger Hilt: Dagger Hilt is a dependency injection library for Android that simplifies dependency injection setup and management
- Room: Room is an Android library that provides an abstraction layer over SQLite databases. It simplifies database operations
- Coroutines: Kotlin Coroutines are a way to write asynchronous and non-blocking code in a more sequential and readable manner.

## Not_Inclued
We exclude some features in app
- Authentication to github 
- Seaching about repoistory
- Add to favorite using github API
- Filtering by programming language
- Sorting by most fork,newest fork,recently update, other

## Nonfuncational
- Using simple UI/UX design
- Detecting internet connection
- High Performance
