package tv.purple.monolith.core.models.flag.variants

import tv.purple.monolith.core.models.flag.core.Variant

enum class ProxyImpl(val value: String) : Variant {
    Disabled("disabled"),
    //TTV_LOL("ttv_lol"),
    //PURPLE("purple"),
    GAY("gay"),
    GAY_PLUS("gay_plus"),
    LUMINOUS_EU("luminous_eu"),
    LUMINOUS_EU2("luminous_eu2"),
    LUMINOUS_AS("luminous_as"),
    PP_EU("pp_eu"),
    PP_EU2("pp_eu2"),
    PP_EU3("pp_eu3"),
    PP_EU4("pp_eu4"),
    PP_EU5("pp_eu5"),
    PP_NA("pp_na"),
    PP_SA("pp_sa"),
    PP_AS("pp_as"),
    CUSTOM("custom");

    override fun getDefault(): Variant {
        return Disabled
    }

    override fun toString(): String {
        return value
    }
}