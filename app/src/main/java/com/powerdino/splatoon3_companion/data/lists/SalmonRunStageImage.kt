package com.powerdino.splatoon3_companion.data.lists

import com.powerdino.splatoon3_companion.R


fun SalmonRunStageImage(
    name:String
): Int {
    when(name){
        "1" -> return R.drawable.salmon1
        "2" -> return R.drawable.salmon2
        "4" -> return R.drawable.salmon3
        "6" -> return R.drawable.salmon4
        "7" -> return R.drawable.salmon5
        "8" -> return R.drawable.salmon6
        "9" -> return R.drawable.salmon7
        "100" -> return R.drawable.stage16
        "102" -> return R.drawable.stage13
        "103" -> return R.drawable.stage4
        "104" -> return R.drawable.stage5
        "105" -> return R.drawable.stage8
        "106" -> return R.drawable.stage2
        "107" -> return R.drawable.stage25
        else -> return R.drawable.unknownmap
    }
}