package camelcase.searchemall

import org.json.JSONObject

class MyJsonHandler(jsonString: String) {

    private val jsonRoot = JSONObject(jsonString)

    /**
     * @arrayName name of array to be extracted from jsonRoot
     * @return jsonArray as ArrayList
     */
    fun getSearchList(arrayName: String): ArrayList<SearchEngineProperties> {
        val arrayList = ArrayList<SearchEngineProperties>()
        val jsonArray = jsonRoot.getJSONArray(arrayName)
        for (i in IntRange(0, jsonArray.length() - 1)) {
            val jsonObj = jsonArray.getJSONObject(i)
            val name = jsonObj.getString("name")
            val url = jsonObj.getString("url")
            val enableJs = jsonObj.getBoolean("enableJs")
            arrayList.add(SearchEngineProperties(name, url, enableJs))
        }
        return arrayList
    }
}