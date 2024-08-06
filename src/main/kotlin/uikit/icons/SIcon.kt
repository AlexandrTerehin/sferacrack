package uikit.icons

enum class SIcon {
    NEW_RELEASE {
        override val value = "icons/ic_40_new_releases.svg"
    },
    RELEASE_ALERT {
        override val value = "icons/ic_40_release_alert.svg"
    },
    BUILD {
        override val value = "icons/ic_40_build.svg"
    },
    FAVORITE {
        override val value = "icons/ic_40_favorite.svg"
    };

    abstract val value: String
}
