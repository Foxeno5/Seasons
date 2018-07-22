package uk.co.harieo.seasons.effects.bad;

import java.util.Collections;
import uk.co.harieo.seasons.models.Effect;
import uk.co.harieo.seasons.models.Weather;

public class SolderingIron extends Effect {

	public SolderingIron() {
		super("Soldering Iron", Collections.singletonList(Weather.SCORCHING), false);
	}

}