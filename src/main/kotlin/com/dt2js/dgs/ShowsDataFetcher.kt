package com.dt2js.dgs

import com.dt2js.dgs.types.Show
import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsQuery
import com.netflix.graphql.dgs.InputArgument
import org.slf4j.LoggerFactory

@DgsComponent
class ShowsDataFetcher {

    companion object {
        val LOG = LoggerFactory.getLogger(ShowsDataFetcher::class.java)
    }

    private val shows = listOf(
        Show("Stranger Things", 2016),
        Show("Ozark", 2017),
        Show("The Crown", 2016),
        Show("Dead to Me", 2019),
        Show("Orange is the New Black", 2013))

    @DgsQuery
    suspend fun shows(@InputArgument titleFilter : String?): List<Show> {

        LOG.info("here")
        return if(titleFilter != null) {
            shows.filter { it.title.contains(titleFilter) }
        } else {
            shows
        }
    }

}
