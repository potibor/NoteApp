package com.task.noteapp.testutil

import com.task.noteapp.testutil.functional.Either
import kotlinx.coroutines.*

abstract class UseCase<out Type : Any, in Params> {

    protected abstract suspend fun buildUseCase(params: Params): Type

    suspend fun run(params: Params): Either<Failure, Type> = withContext(Dispatchers.IO) {
        try {
            Either.Right(buildUseCase(params))
        } catch (failure: Failure) {
            Either.Left(failure)
        }
    }

    object None {
        override fun toString() = "UseCase.None"
    }
}