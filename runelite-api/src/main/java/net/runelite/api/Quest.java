/*
 * Copyright (c) 2019 Abex
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package net.runelite.api;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Quest
{
	BLACK_KNIGHTS_FORTRESS(299, "Black Knights' Fortress"),
	COOKS_ASSISTANT(300, "Cook's Assistant"),
	THE_CORSAIR_CURSE(301, "The Corsair Curse"),
	DEMON_SLAYER(302, "Demon Slayer"),
	DORICS_QUEST(3138, "Doric's Quest"),
	DRAGON_SLAYER_I(3139, "Dragon Slayer I"),
	ERNEST_THE_CHICKEN(305, "Ernest the Chicken"),
	GOBLIN_DIPLOMACY(306, "Goblin Diplomacy"),
	IMP_CATCHER(307, "Imp Catcher"),
	THE_KNIGHTS_SWORD(308, "The Knight's Sword"),
	MISTHALIN_MYSTERY(309, "Misthalin Mystery"),
	PIRATES_TREASURE(310, "Pirate's Treasure"),
	PRINCE_ALI_RESCUE(311, "Prince Ali Rescue"),
	THE_RESTLESS_GHOST(312, "The Restless Ghost"),
	ROMEO__JULIET(313, "Romeo & Juliet"),
	RUNE_MYSTERIES(314, "Rune Mysteries"),
	SHEEP_SHEARER(315, "Sheep Shearer"),
	SHIELD_OF_ARRAV(316, "Shield of Arrav"),
	VAMPYRE_SLAYER(1278, "Vampyre Slayer"),
	WITCHS_POTION(318, "Witch's Potion"),
	X_MARKS_THE_SPOT(3155, "X Marks the Spot"),
	BELOW_ICE_MOUNTAIN(2874, "Below Ice Mountain"),
	ANIMAL_MAGNETISM(331, "Animal Magnetism"),
	ANOTHER_SLICE_OF_HAM(332, "Another Slice of H.A.M."),
	BETWEEN_A_ROCK(333, "Between a Rock..."),
	BIG_CHOMPY_BIRD_HUNTING(334, "Big Chompy Bird Hunting"),
	BIOHAZARD(335, "Biohazard"),
	CABIN_FEVER(336, "Cabin Fever"),
	CLOCK_TOWER(337, "Clock Tower"),
	COLD_WAR(338, "Cold War"),
	CONTACT(339, "Contact!"),
	CREATURE_OF_FENKENSTRAIN(340, "Creature of Fenkenstrain"),
	DARKNESS_OF_HALLOWVALE(341, "Darkness of Hallowvale"),
	DEATH_PLATEAU(342, "Death Plateau"),
	DEATH_TO_THE_DORGESHUUN(343, "Death to the Dorgeshuun"),
	THE_DEPTHS_OF_DESPAIR(344, "The Depths of Despair"),
	DESERT_TREASURE(345, "Desert Treasure"),
	DEVIOUS_MINDS(346, "Devious Minds"),
	THE_DIG_SITE(347, "The Dig Site"),
	DRAGON_SLAYER_II(348, "Dragon Slayer II"),
	DREAM_MENTOR(349, "Dream Mentor"),
	DRUIDIC_RITUAL(350, "Druidic Ritual"),
	DWARF_CANNON(351, "Dwarf Cannon"),
	EADGARS_RUSE(352, "Eadgar's Ruse"),
	EAGLES_PEAK(353, "Eagles' Peak"),
	ELEMENTAL_WORKSHOP_I(354, "Elemental Workshop I"),
	ELEMENTAL_WORKSHOP_II(355, "Elemental Workshop II"),
	ENAKHRAS_LAMENT(356, "Enakhra's Lament"),
	ENLIGHTENED_JOURNEY(357, "Enlightened Journey"),
	THE_EYES_OF_GLOUPHRIE(358, "The Eyes of Glouphrie"),
	FAIRYTALE_I__GROWING_PAINS(359, "Fairytale I - Growing Pains"),
	FAIRYTALE_II__CURE_A_QUEEN(360, "Fairytale II - Cure a Queen"),
	FAMILY_CREST(361, "Family Crest"),
	THE_FEUD(362, "The Feud"),
	FIGHT_ARENA(363, "Fight Arena"),
	FISHING_CONTEST(364, "Fishing Contest"),
	FORGETTABLE_TALE(365, "Forgettable Tale..."),
	BONE_VOYAGE(3135, "Bone Voyage"),
	THE_FREMENNIK_ISLES(367, "The Fremennik Isles"),
	THE_FREMENNIK_TRIALS(368, "The Fremennik Trials"),
	GARDEN_OF_TRANQUILLITY(369, "Garden of Tranquillity"),
	GERTRUDES_CAT(370, "Gertrude's Cat"),
	GHOSTS_AHOY(371, "Ghosts Ahoy"),
	THE_GIANT_DWARF(372, "The Giant Dwarf"),
	THE_GOLEM(373, "The Golem"),
	THE_GRAND_TREE(374, "The Grand Tree"),
	THE_GREAT_BRAIN_ROBBERY(375, "The Great Brain Robbery"),
	GRIM_TALES(376, "Grim Tales"),
	THE_HAND_IN_THE_SAND(377, "The Hand in the Sand"),
	HAUNTED_MINE(378, "Haunted Mine"),
	HAZEEL_CULT(379, "Hazeel Cult"),
	HEROES_QUEST(3142, "Heroes' Quest"),
	HOLY_GRAIL(381, "Holy Grail"),
	HORROR_FROM_THE_DEEP(382, "Horror from the Deep"),
	ICTHLARINS_LITTLE_HELPER(383, "Icthlarin's Little Helper"),
	IN_AID_OF_THE_MYREQUE(384, "In Aid of the Myreque"),
	IN_SEARCH_OF_THE_MYREQUE(385, "In Search of the Myreque"),
	JUNGLE_POTION(386, "Jungle Potion"),
	KINGS_RANSOM(387, "King's Ransom"),
	LEGENDS_QUEST(3145, "Legends' Quest"),
	LOST_CITY(389, "Lost City"),
	THE_LOST_TRIBE(390, "The Lost Tribe"),
	LUNAR_DIPLOMACY(391, "Lunar Diplomacy"),
	MAKING_FRIENDS_WITH_MY_ARM(392, "Making Friends with My Arm"),
	MAKING_HISTORY(393, "Making History"),
	MERLINS_CRYSTAL(394, "Merlin's Crystal"),
	MONKEY_MADNESS_I(395, "Monkey Madness I"),
	MONKEY_MADNESS_II(396, "Monkey Madness II"),
	MONKS_FRIEND(397, "Monk's Friend"),
	MOUNTAIN_DAUGHTER(398, "Mountain Daughter"),
	MOURNINGS_END_PART_I(3147, "Mourning's End Part I"),
	MOURNINGS_END_PART_II(3148, "Mourning's End Part II"),
	MURDER_MYSTERY(401, "Murder Mystery"),
	MY_ARMS_BIG_ADVENTURE(402, "My Arm's Big Adventure"),
	NATURE_SPIRIT(403, "Nature Spirit"),
	OBSERVATORY_QUEST(3149, "Observatory Quest"),
	OLAFS_QUEST(3150, "Olaf's Quest"),
	ONE_SMALL_FAVOUR(406, "One Small Favour"),
	PLAGUE_CITY(407, "Plague City"),
	PRIEST_IN_PERIL(408, "Priest in Peril"),
	THE_QUEEN_OF_THIEVES(409, "The Queen of Thieves"),
	RAG_AND_BONE_MAN_I(3152, "Rag and Bone Man I"),
	RAG_AND_BONE_MAN_II(411, "Rag and Bone Man II"),
	RATCATCHERS(412, "Ratcatchers"),
	RECIPE_FOR_DISASTER(413, "Recipe for Disaster"),
	RECRUITMENT_DRIVE(414, "Recruitment Drive"),
	REGICIDE(415, "Regicide"),
	ROVING_ELVES(416, "Roving Elves"),
	ROYAL_TROUBLE(417, "Royal Trouble"),
	RUM_DEAL(418, "Rum Deal"),
	SCORPION_CATCHER(419, "Scorpion Catcher"),
	SEA_SLUG(420, "Sea Slug"),
	SHADES_OF_MORTTON(421, "Shades of Mort'ton"),
	SHADOW_OF_THE_STORM(422, "Shadow of the Storm"),
	SHEEP_HERDER(423, "Sheep Herder"),
	SHILO_VILLAGE(424, "Shilo Village"),
	THE_SLUG_MENACE(425, "The Slug Menace"),
	A_SOULS_BANE(426, "A Soul's Bane"),
	SPIRITS_OF_THE_ELID(427, "Spirits of the Elid"),
	SWAN_SONG(428, "Swan Song"),
	TAI_BWO_WANNAI_TRIO(429, "Tai Bwo Wannai Trio"),
	A_TAIL_OF_TWO_CATS(430, "A Tail of Two Cats"),
	TALE_OF_THE_RIGHTEOUS(431, "Tale of the Righteous"),
	A_TASTE_OF_HOPE(432, "A Taste of Hope"),
	TEARS_OF_GUTHIX(433, "Tears of Guthix"),
	TEMPLE_OF_IKOV(434, "Temple of Ikov"),
	THRONE_OF_MISCELLANIA(435, "Throne of Miscellania"),
	THE_TOURIST_TRAP(436, "The Tourist Trap"),
	TOWER_OF_LIFE(437, "Tower of Life"),
	TREE_GNOME_VILLAGE(438, "Tree Gnome Village"),
	TRIBAL_TOTEM(439, "Tribal Totem"),
	TROLL_ROMANCE(440, "Troll Romance"),
	TROLL_STRONGHOLD(441, "Troll Stronghold"),
	UNDERGROUND_PASS(442, "Underground Pass"),
	CLIENT_OF_KOUREND(3136, "Client of Kourend"),
	WANTED(444, "Wanted!"),
	WATCHTOWER(445, "Watchtower"),
	WATERFALL_QUEST(3154, "Waterfall Quest"),
	WHAT_LIES_BELOW(447, "What Lies Below"),
	WITCHS_HOUSE(448, "Witch's House"),
	ZOGRE_FLESH_EATERS(449, "Zogre Flesh Eaters"),
	THE_ASCENT_OF_ARCEUUS(542, "The Ascent of Arceuus"),
	THE_FORSAKEN_TOWER(543, "The Forsaken Tower"),
	SONG_OF_THE_ELVES(603, "Song of the Elves"),
	THE_FREMENNIK_EXILES(3141, "The Fremennik Exiles"),
	SINS_OF_THE_FATHER(1276, "Sins of the Father"),
	A_PORCINE_OF_INTEREST(3151, "A Porcine of Interest"),
	GETTING_AHEAD(752, "Getting Ahead"),
	A_KINGDOM_DIVIDED(2971, "A Kingdom Divided"),
	A_NIGHT_AT_THE_THEATRE(949, "A Night at the Theatre"),
	LAND_OF_THE_GOBLINS(4135, "Land of the Goblins"),
	ENTER_THE_ABYSS(3140, "Enter the Abyss"),
	ARCHITECTURAL_ALLIANCE(320, "Architectural Alliance"),
	BEAR_YOUR_SOUL(1275, "Bear Your Soul"),
	ALFRED_GRIMHANDS_BARCRAWL(322, "Alfred Grimhand's Barcrawl"),
	CURSE_OF_THE_EMPTY_LORD(3137, "Curse of the Empty Lord"),
	THE_ENCHANTED_KEY(324, "The Enchanted Key"),
	THE_GENERALS_SHADOW(325, "The General's Shadow"),
	SKIPPY_AND_THE_MOGRES(3153, "Skippy and the Mogres"),
	MAGE_ARENA_I(3146, "Mage Arena I"),
	LAIR_OF_TARN_RAZORLOR(3144, "Lair of Tarn Razorlor"),
	FAMILY_PEST(329, "Family Pest"),
	MAGE_ARENA_II(330, "Mage Arena II"),
	IN_SEARCH_OF_KNOWLEDGE(3143, "In Search of Knowledge"),
	DADDYS_HOME(1688, "Daddy's Home"),
	THE_FROZEN_DOOR(3768, "The Frozen Door"),
	HOPESPEARS_WILL(4136, "Hopespear's Will"),
	TEMPLE_OF_THE_EYE(4273, "Temple of the Eye"),
	;

	@Getter
	private final int id;

	@Getter
	private final String name;

	public QuestState getState(Client client)
	{
		client.runScript(ScriptID.QUEST_STATUS_GET, id);
		switch (client.getIntStack()[0])
		{
			case 2:
				return QuestState.FINISHED;
			case 1:
				return QuestState.NOT_STARTED;
			default:
				return QuestState.IN_PROGRESS;
		}
	}
}
