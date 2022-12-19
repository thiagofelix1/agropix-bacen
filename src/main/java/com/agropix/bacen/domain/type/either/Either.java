package com.agropix.bacen.domain.type.either;

public interface Either<TLeft, TRight> {

        TLeft unsafeGetLeft();
        TRight unsafeGetRight();
        boolean isLeft();
        boolean isRight();
}
