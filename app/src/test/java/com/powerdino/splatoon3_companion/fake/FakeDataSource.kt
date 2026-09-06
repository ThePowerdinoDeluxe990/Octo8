package com.powerdino.splatoon3_companion.fake

import com.powerdino.splatoon3_companion.model.Bankara
import com.powerdino.splatoon3_companion.model.BankaraOpen
import com.powerdino.splatoon3_companion.model.Data
import com.powerdino.splatoon3_companion.model.Fest
import com.powerdino.splatoon3_companion.model.League
import com.powerdino.splatoon3_companion.model.Normal
import com.powerdino.splatoon3_companion.model.Regular
import com.powerdino.splatoon3_companion.model.X
import com.powerdino.splatoon3_companion.model.salmon_run.RewardGear
import com.powerdino.splatoon3_companion.model.salmon_run.Salmon
import com.powerdino.splatoon3_companion.model.salmon_run.SalmonNormal
import com.powerdino.splatoon3_companion.model.salmon_run.resources.Enemy
import com.powerdino.splatoon3_companion.model.salmon_run.resources.Modes
import com.powerdino.splatoon3_companion.model.salmon_run.resources.SalmonResources

object FakeDataSource {
    fun getSplatoonData(): Data{
        return Data(
            normal = listOf<Normal>(
                Normal(
                        bankara = Bankara(
                            rule = "Area",
                            stages = listOf(
                                12, 14, 1, 5,
                            ),
                        ),
                bankaraOpen = BankaraOpen(
                    rule = "Area",
                    stages = listOf(
                        12, 3, 4,
                    ),
                ),
                endTime = "endTime",
                startTime = "startTime",
                league = League(
                    eventId = "1",
                    eventType = "especial",
                    rule = "test",
                    stages = listOf(),
                ),
                regular = Regular(
                    rule = "paint",
                    stages = listOf(
                        12,
                        10,
                        13,
                    ),
                ),
                phaseId = "test",
                x = X(
                    rule = "test",
                    stages = listOf(
                        12, 1, 3,
                    ),
                ),
                )
            ),
            fest = Fest,
        )
    }

    fun getSalmonResources(): SalmonResources {
        return SalmonResources(
            enemy = Enemy(
                sakediver = "",
                sakedozer = "",
                sakeArtillery = " ",
                sakeBigMouth = " ",
                sakeBigMouthGold = " ",
                sakeCopter = " ",
                sakeDolphin = " ",
                sakeFlyBagman = " ",
                sakeJaw = " ",
                sakePillar = " ",
                sakeRope = " ",
                sakeSaucer = " ",
                sakelienBomber = " ",
                sakelienCupTwins = " ",
                sakelienGiant = " ",
                sakelienGolden = " ",
                sakelienLarge = " ",
                sakelienShield = " ",
                sakelienSmall = " ",
                sakelienSnake = " ",
                sakelienStandard = " ",
                sakelienTower = " ",
                sakerocket = " ",
                triple = " ",
            ),
            events = Events(
                dozer = " ",
                fog = " ",
                geyser = " ",
                hakobiya = " ",
                missile = " ",
                relay = " ",
                rush = " ",
                tamaire = " "
            ),
            gearclothes = mapOf<String, String>(),
            gearhead = mapOf<String, String>(),
            gearshoes = mapOf<String, String>(),
            locker = mapOf<String, String>(),
            modes = Modes(
                "",
                "",
                ""
            ),
            stages = mapOf<String, String>(),
            tides = Tides(
                "",
                "",
                "",
            ),
            weaponsmain = mapOf<String, String>(),
            weaponsspecial = mapOf<String, String>()
        )
    }

    fun getSalmonShedules(): Salmon{
        return Salmon(
            bigRun = listOf<String>(),
            normal = listOf<SalmonNormal>(
                SalmonNormal(
                    bigBoss = "",
                    endTime = "",
                    phaseId = "",
                    rareWeapons = listOf<String>(),
                    rewardGear = RewardGear(
                        1,
                        "",
                        ""
                    ),
                    stage = 1,
                    startTime = "",
                    weapons = listOf<Int>(),
                )
            ),
            teamContest =  listOf<String>()
        )
    }
}