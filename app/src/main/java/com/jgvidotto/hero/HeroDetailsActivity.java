package com.jgvidotto.hero;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.jgvidotto.hero.ui.main.Model.CharacterModel;
import com.squareup.picasso.Picasso;

public class HeroDetailsActivity extends AppCompatActivity {

    private ImageView heroImage;
    private TextView heroDetails;
    private CharacterModel mCharacter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hero_details);
        mCharacter = (CharacterModel) getIntent().getSerializableExtra("hero_object");

        heroImage = findViewById(R.id.heroImageDetail);
        heroDetails = findViewById(R.id.heroDetails);

        Picasso.get().load(mCharacter.getThumbnail().getPathBig()).into(heroImage);
        heroDetails.setText(mCharacter.getDescription().equals("") ? "No description available" : mCharacter.getDescription());
    }
}
