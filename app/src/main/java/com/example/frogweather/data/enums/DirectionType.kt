package com.example.frogweather.data.enums

import com.example.frogweather.R

enum class DirectionType(val directionType: Int, val resourceId: Int) {
    N(R.string.lbl_direction_n, R.drawable.ic_arrow_n),
    NNE(R.string.lbl_direction_nne, R.drawable.ic_arrow_ne),
    NE(R.string.lbl_direction_ne, R.drawable.ic_arrow_ne),
    ENE(R.string.lbl_direction_ene, R.drawable.ic_arrow_ne),
    E(R.string.lbl_direction_e, R.drawable.ic_arrow_e),
    ESE(R.string.lbl_direction_ese, R.drawable.ic_arrow_se),
    SE(R.string.lbl_direction_se, R.drawable.ic_arrow_se),
    SSE(R.string.lbl_direction_sse, R.drawable.ic_arrow_se),
    S(R.string.lbl_direction_s, R.drawable.ic_arrow_s),
    SSW(R.string.lbl_direction_ssw, R.drawable.ic_arrow_sw),
    SW(R.string.lbl_direction_sw, R.drawable.ic_arrow_sw),
    WSW(R.string.lbl_direction_wsw, R.drawable.ic_arrow_sw),
    W(R.string.lbl_direction_w, R.drawable.ic_arrow_w),
    WNW(R.string.lbl_direction_wnw, R.drawable.ic_arrow_nw),
    NW(R.string.lbl_direction_nw, R.drawable.ic_arrow_nw),
    NNW(R.string.lbl_direction_nnw, R.drawable.ic_arrow_nw);

    companion object {
        fun getByDirectionDegrees(degrees: Int): DirectionType {
            return when {
                348.75 <= degrees && degrees < 11.25 -> N
                11.25 <= degrees && degrees < 33.75 -> NNE
                33.75 <= degrees && degrees < 56.25 -> NE
                56.25 <= degrees && degrees < 78.75 -> ENE
                78.75 <= degrees && degrees < 101.25 -> E
                101.25 <= degrees && degrees < 123.75 -> ESE
                123.75 <= degrees && degrees < 146.25 -> SE
                146.25 <= degrees && degrees < 168.75 -> SSE
                168.75 <= degrees && degrees < 191.25 -> S
                191.25 <= degrees && degrees < 213.75 -> SSW
                213.75 <= degrees && degrees < 236.25 -> SW
                236.25 <= degrees && degrees < 258.75 -> WSW
                258.75 <= degrees && degrees < 281.25 -> W
                281.25 <= degrees && degrees < 303.75 -> WNW
                303.75 <= degrees && degrees < 326.25 -> NW
                326.25 <= degrees && degrees < 358.75 -> NNW
                else -> N
            }
        }
    }
}