Donders mooie plugin'tje

Features:
- Villager spawn't als er iets in de buurt is (Voor debug nu dus een Pig)
- Villager heeft een ingestelde waarde van 10 seconden (Voor debug) waarna hij zal despawnen
- Als de villager dood gaat dropt hij de loot van de speler (incl armour slots offhand etc) 
- Als de villager gehit word zal de timer gereset worden en weer despawen
- Als de speler inlogt despawnt de villager (ah duh)
- Als de server restart despawnt de npc ook. Mocht de server crashen dan zal de NPC geen loot droppen nadat hij gekillt is.

TODO: 
- Nadat de NPC gekillt is de speler ook killen + inventory clearen
- Nadat de server crasht de NPC 'neutraal' maken zodat hier niet mee ge-exploit kan worden. (Breeden, leads etc). #InteractEvent checkje
- Config file maken met instellingen voor de PvpLog-module
- Logging toepassen zodat er terug gezien kan worden of de villager gekillt, gezien en door wie, gehit (en door wie) en of gespawnt is.
- Leuke particle effects bij spawnen, despawnen en om de zoveel tick een firework effect zodat de locatie makkeijker gekenmerkt is.
- Optimalisatie van de code om zo veel mogelijk ticks te besparen

Dependencies:
- BKCommonLib - https://www.spigotmc.org/resources/bkcommonlib.39590/

![GitHub Logo](/images/logo.png)
Format: ![Alt Text](url)
