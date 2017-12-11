package facture;

import java.util.HashMap;
import java.util.Map.Entry;

public class Catalogue {

    HashMap<String, Article> catalogue = new HashMap<>();

    public void addArticle(Article article) {
        catalogue.put(article.getCode(), article);
    }

    public Article findByCode(String code) {
        for (Entry<String,Article> entry : catalogue.entrySet()) {
            if (entry.getKey() == code) {
                return entry.getValue();
            }
            
        }
        return null;
     
    }

}
