package com.dt2js.dgs

import com.dt2js.dgs.types.Show
import com.dt2js.dgs.types.Theater
import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsData
import com.netflix.graphql.dgs.DgsQuery
import com.netflix.graphql.dgs.InputArgument
import graphql.schema.DataFetchingEnvironment
import org.slf4j.LoggerFactory

@DgsComponent
class TheatersDataFetcher {

    companion object {
        val LOG = LoggerFactory.getLogger(TheatersDataFetcher::class.java)
    }

    val the_theaters = listOf<Theater>(
        Theater(name = "theater one", shows = listOf(

            Show("Stranger Things", 2016),
            Show("Ozark", 2017),

        )),
        Theater(name = "theater two", shows = listOf(

            Show("The Crown", 2016),
            Show("Dead to Me", 2019),
        )),
    )

    @DgsQuery
    suspend fun theaters() : List<Theater> {

        return the_theaters
    }

    @DgsData(parentType = DgsConstants.THEATER.TYPE_NAME, field = DgsConstants.THEATER.Shows)
    suspend fun shows(@InputArgument filter : String?, dfe: DataFetchingEnvironment): List<Show> {
        val theater = dfe.getSource<Theater>()

        LOG.info(filter)

        return theater.shows
    }
}