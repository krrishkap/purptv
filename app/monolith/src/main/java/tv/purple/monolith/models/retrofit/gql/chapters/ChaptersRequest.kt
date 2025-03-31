package tv.purple.monolith.models.retrofit.gql.chapters

data class ChaptersRequest(
    val videoId: String
) {
    val query: String = "query ChapterSelectButton(\$videoID: ID \$includePrivate: Boolean = false) {\n" +
            "    video(id: \$videoID options: { includePrivate: \$includePrivate }) {\n" +
            "        id\n" +
            "        game {\n" +
            "            id\n" +
            "            displayName\n" +
            "            boxArtURL(width: 160 height: 212)\n" +
            "        }\n" +
            "        moments(momentRequestType: VIDEO_CHAPTER_MARKERS types: [GAME_CHANGE]) {\n" +
            "            edges {\n" +
            "                node {\n" +
            "                    id\n" +
            "                    durationMilliseconds\n" +
            "                    positionMilliseconds\n" +
            "                    type\n" +
            "                    description\n" +
            "                    details {\n" +
            "                        ... on GameChangeMomentDetails {\n" +
            "                            game {\n" +
            "                                id\n" +
            "                                displayName\n" +
            "                                boxArtURL(width: 160 height: 212)\n" +
            "                            }\n" +
            "                        }\n" +
            "                    }\n" +
            "                    video {\n" +
            "                        id\n" +
            "                        lengthSeconds\n" +
            "                    }\n" +
            "                }\n" +
            "            }\n" +
            "        }\n" +
            "    }\n" +
            "}\n"
    val variables: Map<String, Any> = mapOf("videoID" to videoId)
}
