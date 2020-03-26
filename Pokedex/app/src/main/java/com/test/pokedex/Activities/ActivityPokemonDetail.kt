package com.test.pokedex.Activities

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.gson.JsonArray
import com.koushikdutta.ion.Ion
import com.test.pokedex.Adapters.AdapterList
import com.test.pokedex.R
import kotlinx.android.synthetic.main.activity_list.*
import kotlinx.android.synthetic.main.activity_pokemon_detail.*
import org.json.JSONArray
import org.json.JSONObject


class ActivityPokemonDetail : AppCompatActivity(){

    private var context: Context = this
    private lateinit var data:JsonArray
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter:AdapterList
    private lateinit var view: ImageView
    private lateinit var view2: ImageView
    private lateinit var pokemon_name: ImageView
    private lateinit var gridLayoutManager: GridLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
       // gridLayoutManager.scrollVerticallyBy(0,,)
        setContentView(R.layout.activity_pokemon_detail)
        setSupportActionBar(toolbar)



        //get the intent JSON STRING with API information
        val intent = intent
        val jsonString = intent.getStringExtra("result")

        //Get all information. Convert JSON String to JSONObject
        var pokemonInfo = JSONObject(intent.getStringExtra("result"))

        //Get every specific field for the pokemon

        //Get name
        var nombre = pokemonInfo.get("name")

        //Get number
        var number = pokemonInfo.get("id")
        println("POKEMON INFO IS HERE : $number")
        //Get Sprites
        var spriteFront = pokemonInfo.getJSONObject("sprites").get("front_default")
        var spriteBack = pokemonInfo.getJSONObject("sprites").get("back_default")

        //Get Types, with for
        var types = pokemonInfo.getJSONArray("types")
       // println(types)
        var list_types: String
        var type1 = findViewById<TextView>(R.id.type)
        for (i in 0 until types.length()) {
            val item = types.getJSONObject(i).getJSONObject("type").getString("name")
           // println("MIRA: $item")
            type1.text = type1.text.toString() + item + "\n"

        }

        //Get Stats with For
        var stats = pokemonInfo.getJSONArray("stats")
        var stats_view = findViewById<TextView>(R.id.stats_view)
        //var name_stat =  stats.getJSONObject(0).getJSONObject("stat").getString("name")

        for (i in 0 until stats.length()) {
            var item_stat = stats.getJSONObject(i).getJSONObject("stat").getString("name")
            var item_base = stats.getJSONObject(i).getString("base_stat")
            var item_effort = stats.getJSONObject(i).getString("effort")
            //stats.getJSONObject(0).getString("base_stat")
           // println("MIRA: $item_stat")
            stats_view.text = "\t*"+stats_view.text.toString() + "\n" +
                    "\t*" + "Name stat: " + item_stat+ "\n" +
                    "\t*" + "Effort: " + item_effort + "\n" +
                    "\t*" + "Base_stat:" + item_base + "\n\n"

        }

        //var spriteFront = pokemonInfo.getJSONObject("sprites").get("front_default")
        // var movements = pokemonInfo.getJSONArray("moves").getJSONObject(0).getJSONObject("move").getString("name")
        //println("MOVEMENTS DEL POKEMON: $movements")

        //var moves_view = findViewById<TextView>(R.id.moves_view)
        //moves_view.setText(movements)


        var moves = pokemonInfo.getJSONArray("moves")
        var moves_view = findViewById<TextView>(R.id.moves_view)
        //var name_stat =  stats.getJSONObject(0).getJSONObject("stat").getString("name")

        for (i in 0 until moves.length()) {

            var item_name = moves.getJSONObject(i).getJSONObject("move").getString("name")
            moves_view.text = 	moves_view.text.toString() + item_name+ "\n"
            //println("MIRA: $item_name")
        }




        //show name

        var pokemon_name = findViewById<TextView>(R.id.pokemon_name_detail)
        pokemon_name.setText(nombre.toString())

        //show nnumber
        var pokemon_number_view = findViewById<TextView>(R.id.pokemon_number)
        pokemon_number_view.setText("Number: " + number.toString())

        //Show sprites
        var view = findViewById<ImageView>(R.id.front)
        var view2 = findViewById<ImageView>(R.id.back)

        Glide
            .with(context)
            .load(spriteFront.toString())
            .placeholder(R.drawable.pokemon_logo_min)
            .error(R.drawable.pokemon_logo_min)
            .into(view);

        Glide
            .with(context)
            .load(spriteBack.toString())
            .placeholder(R.drawable.pokemon_logo_min)
            .error(R.drawable.pokemon_logo_min)
            .into(view2);





    }

}
