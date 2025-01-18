package mahiro76.mahiro.datageneration;

import mahiro76.mahiro.advancements.Advancements;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancement.Advancement;
import java.util.function.Consumer;

public class MahiroAdvancementsProvider extends FabricAdvancementProvider {

    protected MahiroAdvancementsProvider(FabricDataOutput dataGenerator) {
        super(dataGenerator);
    }

    @Override
    public void generateAdvancement(Consumer<Advancement> consumer) {
        new Advancements().accept(consumer);
    }

}
