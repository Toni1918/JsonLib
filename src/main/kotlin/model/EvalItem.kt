package model

data class EvalItem(val weight: Double, val type: EvalType)

enum class EvalType {
    TEST, PROJECT
}
