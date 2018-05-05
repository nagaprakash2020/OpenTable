package com.ndanda.opentable.data

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity

// Since there is no primary key associated with results Data. Currently using a composite primary key
// Which is a combination of "display_title" and "summary_short"
@Entity(primaryKeys = ["display_title","summary_short"])
class Results {
    var display_title: String = ""
    var mpaa_rating: String? = null
    var critics_pick: Int = 0
    var byline: String? = null
    var headline: String? = null
    var summary_short: String = ""
    var publication_date: String? = null
    var opening_date: String? = null
    var date_updated: String? = null
    @Embedded(prefix = "link_")
    var link: Link? = null
    @Embedded(prefix = "multimedia_")
    var multimedia: Multimedia? = null
}
