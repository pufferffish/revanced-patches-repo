rootProject.name = "revanced-patches-repo"

buildCache {
    local {
        isEnabled = "CI" !in System.getenv()
    }
}
