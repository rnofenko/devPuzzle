package rn.puzzle.graph.medium

import org.junit.Assert
import org.junit.Test
import rn.tool.FileHelper
import rn.tool.Rand
import rn.tool.Stopwatch
import rn.tool.StringToArrayConverter.stringTo2dIntArray

class OptimizeWaterDistributionInAVillage {
    @Test
    fun test1() {
        val res = solve(intArrayOf(1, 2, 2), arrayOf(intArrayOf(1, 2, 1), intArrayOf(2, 3, 1)))
        Assert.assertEquals(3, res)
    }

    @Test
    fun test2() {
        val wells = intArrayOf(17386,10607,94654,21075,2021,20691,20012,91868,23511,5030,56435,52047,8473,69744,82958,99685,13007,86013,89197,47816,30796,7649,26211,81807,39860,64969,61850,52995,87595,49717,1980,12110,91942,9559,64178,96996,24969,77272,24005,47509,29637,67056,91245,96379,99843,65007,16996,31474,32225,35236,53480,56826,85588,40871,59929,76131,58905,18241,93290,31692,45447,70817,20402,68412,82775,52495,3649,21025,72732,37237,96507,49999,15320,29478,96123,27197,3937,12655,32857,92619,44751,38817,35162,29200,629,23929,54457,82190,14192,62164,83521,43569,80418,90696,35225,44451,35551,14506,11934,55251,46611,17771,22569,11163,10727,40321,22850,37753,10073,59412,24978,31998,33035,36476,71355,32670,48884,82077,736,52118,25708,75183,47574,45600,34893,36791,35727,10531,14424,18509,85489,84745,73106,28389,47949,53584,43323,8482,96295,24729,88323,14341,9193,79060,55665,27265,77529,88631,44505,68001,74896,80672,8754,17741,75737,30532,26720,13596,73351,83679,44633,52592,43530,85251,55460,90451,63205,39249,2652,87005,47089,78372,78701,41079,44466,54459,66294,536,11753,92396,5501,11525,58364,42386,83350,93935,40547,39418,76414,28115,43692,30126,19191,53568,93903,10981,25605,26945,89785,93884,8951,72751,2843,83310,67898,16582,24491,18789,78072,9879,46456,90283,79939,94101,72823,81388,32345,87753,16176,47142,42076,27667,21904,82188,9081,46385,34178,40293,90310,73940,18268,86638,48683,75449,26765,46704,1016,52546,247,98522,9256,10085,26670,9885,94927,3953,89600,89189,21450,18677,95499,9370,19981,68101,29385,24949,72279,78658,93394,85498,91679,38563,21083,30338,80734,2070,30879,53264,22736,10909,14843,86866,96065,734,77859,54651,7390,97346,67505,92213,11927,59637,47545,54028,65711,46089,4145,29490,12969,35457,24648,78669,87776,87843,23147,15321,77336,66016,68351,853,24073,15205,63654,69380,19935,28206,31252,87191,25881,58613,56275,31876,69059,49300,63865,79872,41086,10650,48102,47574,90793,77276,52786,76064,65144,47925,63618,20304,63223,30860,91955,10196,16499,11428,15382,8543,92649,59052,53143,48427,98919,89221,2565,17146,94186,90520,94975,79777,2467,63586,84163,15078,44545,94904,39210,76781,48726,33047,14893,97744,4477,6304,66404,82048,32236,92506,99980,64471,49117,25255,70492,73919,45309,99653,72233,1843,9158,20050,85597,85668,45342,51543,42196,6054,1205,44561,84362,56476,33337,53319,25460,87097,37011,83364,75235,50039,79477,57433,88524,51702,72644,46074,32626,33359,77507,30762,96571,50816,33684,98818,56968,67230,16692,20109,90447,85069,13856,51796,86978,37599,64235,51752,74470,86897,66579,58055,75885,42857,10382,73273,62135,65911,77152,22271,80101,61736,50829,51720,48685,9293,83449,26996,39742,95749,10621,93134,16555,25802,69941,41112,64975,25898,5677,13769,48944,57451,56628,27954,66441,80884,68685,36076,81835,43032,89139,55072,92660,55030,70478,61911,14346,41333,4822,26830,17675,59649,65424,88797,46295,31533,99943,85732,39386,9999,35766,46034,93454,86971,87573,24497,2591,95018,24335,96454,29732,75353,99561,40166,96179,25445,32752,5166,65533,35124,23414,38754,53567,27571,82839,47358,96363,50300,58901,12600,44569,19922,39078,34370,19732,75755,87941,1424,75597,50109,29615,84380,84279,35621,76683,57375,91726,30066,32342,98900,79703,79074,46967,41239,69971,82809,8781,39159,7389,63088,90801,69034,77175,69860,74276,92301,13110,29173,34337,82924,53251,72286,75788,62001,7860,19875,28034,29135,24421,17479,101,62067,35399,36764,94965,20782,10108,16665,18076,54852,31648,59345,67937,32631,40828,72939,69626,46097,52692,31117,79701,68969,76765,39429,16416,3128,46181,85278,10422,48346,17904,91946,2887,70432,36362,48155,71235,21549,16785,79301,186,29191,81856,67318,62709,32055,81867,47032,31293,78477,23233,61056,20917,47335,55969,22333,12741,28657,15522,41310,27347,47366,62689,32834,30304,90531,20975,99151,53913,3692,16883,42187,98094,74503,79344,56859)
        val pipsStr = FileHelper.load("/Users/tkma0vh/Downloads/pipes.txt")[0]
        val pipes = stringTo2dIntArray(pipsStr)
        compare(wells, pipes)
    }

    @Test
    fun randCompare() {
        for(i in 0..1000000) {
            val wells = Rand.newArray(20)
            val pipes = Rand.graphEdges(30, 1, wells.size)
            compare(wells, pipes)
        }
    }

    private fun compare(wells: IntArray, pipes: Array<IntArray>) {
        val w = Stopwatch.start()
        val r1 = loop.minCostToSupplyWater(wells, pipes)
        w.show("r1").start()
        val r2 = adjList.minCostToSupplyWater(wells, pipes)
        w.show("r2")
        if (r1 != r2) {
            Assert.assertEquals(r1, r2)
        }
    }

    private fun solve(wells: IntArray, initialPipes: Array<IntArray>): Int {
        return adjList.minCostToSupplyWater(wells, initialPipes)
    }

    private val loop = OptimizeWaterDistributionInAVillageLoop()
    private val adjList = OptimizeWaterDistributionInAVillageAdjList()
}