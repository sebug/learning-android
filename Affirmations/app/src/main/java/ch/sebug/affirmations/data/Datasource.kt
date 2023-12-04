package ch.sebug.affirmations.data

import ch.sebug.affirmations.R
import ch.sebug.affirmations.model.Affirmation

class Datasource {
    fun loadAffirmations(): List<Affirmation> {
        return listOf<Affirmation>(
            Affirmation(R.string.affirmation_0, R.drawable.cat_0),
            Affirmation(R.string.affirmation_1, R.drawable.cat_1),
            Affirmation(R.string.affirmation_2, R.drawable.cat_2),
            Affirmation(R.string.affirmation_3, R.drawable.cat_3),
            Affirmation(R.string.affirmation_4, R.drawable.cat_4),
            Affirmation(R.string.affirmation_5, R.drawable.cat_5),
            Affirmation(R.string.affirmation_6, R.drawable.cat_6),
            Affirmation(R.string.affirmation_7, R.drawable.cat_7),
            Affirmation(R.string.affirmation_8, R.drawable.cat_8),
            Affirmation(R.string.affirmation_9, R.drawable.cat_9)
        )
    }
}