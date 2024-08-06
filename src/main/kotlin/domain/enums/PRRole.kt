package domain.enums

internal enum class PRRole {
    AUTHOR {
        override fun get() = "author"
    },
    REVIEWER {
        override fun get() = "reviewer"
    };

    abstract fun get(): String
}