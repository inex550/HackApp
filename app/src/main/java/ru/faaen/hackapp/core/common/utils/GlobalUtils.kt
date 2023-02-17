package ru.faaen.hackapp.core.common.utils

fun <T> uiLazy(block: () -> T) = lazy(LazyThreadSafetyMode.NONE, block)