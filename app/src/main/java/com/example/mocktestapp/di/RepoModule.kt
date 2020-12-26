package com.example.mocktestapp.di

import com.example.mocktestapp.repository.Repo
import org.koin.dsl.module

val repoModule = module {
    factory { Repo(get()) }
}