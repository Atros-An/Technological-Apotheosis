package net.mmg_atros_an.technological_apotheosis.items;

import com.gregtechceu.gtceu.api.item.MetaMachineItem;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;


public class TestItem extends Item {


    public TestItem(Properties pProperties) {
        super(pProperties);
    }

//    @Override
//    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
//
//        pPlayer.getItemInHand(pUsedHand).shrink(1);
//
//        ItemStack itemStack = pPlayer.getOffhandItem();
//        MetaMachineItem machine = getMachine(itemStack);
//        if(machine!=null) {
//            LootRarity rarity = RarityRegistry.getMaterialRarity(ForgeRegistries.ITEMS.getValue(ResourceLocation.fromNamespaceAndPath("apotheosis","common_material"))).getOptional().orElse(null);
////            setMultiplier(itemStack,2.0);
//            LootCategory cat = LootCategory.forItem(itemStack);
//            AffixType type= AffixType.ABILITY;
//            List<DynamicHolder<Affix>> affixList = AffixHelper.byType(type)
//                    .stream()
////                    .filter(a -> a.get().canApplyTo(itemStack, cat, rarity))
//                    .collect(Collectors.toList());
//            for(DynamicHolder<Affix> affixHolder:affixList){
//                Affix affix = affixHolder.getOptional().orElse(null);
//                if(affix!=null) {
//                    TechnologicalApotheosis.LOGGER.debug(affix.getId().toString());
//                }
//            }
//        }
//        return super.use(pLevel, pPlayer, pUsedHand);
//    }



    public static MetaMachineItem getMachine(ItemStack stack) {
        if (stack.isEmpty()) {
            return null;
        }

        if (!(stack.getItem() instanceof BlockItem blockItem)) {
            return null;
        }

        return blockItem instanceof MetaMachineItem metaMachineItem ? metaMachineItem : null;
    }

    private static final String TAG_KEY = "TechnologicalApotheosis";
    private static final String MULTIPLIER_KEY = "Multiplier";

    public static double getMultiplier(ItemStack stack) {
        CompoundTag tag = stack.getTag();

        if (tag == null ||
                !tag.contains(TAG_KEY, CompoundTag.TAG_COMPOUND)) {
            return 1.0;
        }

        CompoundTag affixTag = tag.getCompound(TAG_KEY);

        return affixTag.contains(MULTIPLIER_KEY, CompoundTag.TAG_DOUBLE)
                ? affixTag.getDouble(MULTIPLIER_KEY)
                : 1.0;
    }

    public static void setMultiplier(ItemStack stack, double multiplier) {
        CompoundTag tag = stack.getOrCreateTag();

        CompoundTag affixTag = tag.getCompound(TAG_KEY);
        affixTag.putDouble(MULTIPLIER_KEY, multiplier);

        tag.put(TAG_KEY, affixTag);
    }

}
