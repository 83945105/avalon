package pub.avalonframework.redis.beans;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 白超 on 2019/11/27.
 */
public class IllustratedHandbook {

    private List<Pokemon> pokemons = new ArrayList<>(386);

    public List<Pokemon> getPokemons() {
        return pokemons;
    }

    public void setPokemons(List<Pokemon> pokemons) {
        this.pokemons = pokemons;
    }

    public void addPokemon(Pokemon pokemon) {
        this.pokemons.add(pokemon);
    }
}