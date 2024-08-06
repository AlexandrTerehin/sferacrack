package domain.enums

internal enum class PRStatus {
    ALL {
        override fun get() = "all"
    },
    OPEN {
        override fun get() = "open"
    },
    CLOSE {
        override fun get() = "close"
    },
    MERGED {
        override fun get() = "merged"
    },
    REJECTED {
        override fun get() = "rejected"
    };

    abstract fun get(): String
}