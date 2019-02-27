(ns palivovnik.data.cars)

(def marques-countries
    ["Česká republika", "Německo", "Velká Británie", "Rusko", "USA", "Španělsko", "Francie", "Švédsko",
           "Polsko", "Maďarsko", "Japonsko", "Jižní Korea", "Itálie", "Indie", "Čína", "Rumunsko", "Ukrajina"])

(def fuel-categories
    {:petrol "Benzín" :diesel "Nafta" :lpg "LPG" :cng "CNG" :e85 "E85"})

(def fillup-fuels
    {:n95 "Natural 95" :n98 "Natural 98" :n100 "Natural 100" :e85 "E85" :e10 "E10" :diesel "Nafta" :lpg "LPG" :cng "CNG"})

(def engine-aspirations
    {:natural "žádné (atmosférický motor)" :turbocharger "turbo" :supercharger "kompresor"})

(def engine-configs
    {:V "V (vidlicový)" :W "W (dvouvidlicový)" :H "H (boxer)" :L "L (řadový)"})

(def transmissions
    {:manual "manuální" :automatic "automatická" :cvt "CVT / variátor"})

(def engine-cyls
    [1 2 3 4 5 6 8 10 12 16])

(def marques-models
(apply sorted-map [
 "AC" ["427"],
 "Abarth" ["1000" "500" "Grande Punto" "Punto EVO"],
 "Acura"
 ["CL"
  "CSX"
  "EL"
  "ILX"
  "Integra"
  "Legend"
  "MDX"
  "NSX"
  "RDX"
  "RL"
  "RSX"
  "SLX"
  "TL"
  "TSX"
  "Vigor"
  "ZDX"],
 "Alfa Romeo"
 ["145"
  "146"
  "147"
  "155"
  "156"
  "159"
  "166"
  "Alfetta"
  "Brera"
  "Giulietta"
  "GT"
  "GTA"
  "GTV"
  "GTV-6"
  "MiTo"
  "Spider"],
 "American Motors" ["Eagle" "Gremlin"],
 "Aston Martin" ["DB9" "V8 Vantage"],
 "Audi"
 ["100"
  "100 Quattro"
  "200 Quattro"
  "4000 Quattro"
  "5000"
  "5000 Quattro"
  "80"
  "80 Quattro"
  "90"
  "90 Quattro"
  "A1"
  "A2"
  "A3"
  "A4"
  "A4 Quattro"
  "A5 Quattro"
  "A6"
  "A6 Quattro"
  "A7"
  "A8"
  "A8 Quattro"
  "Allroad Quattro"
  "Cabriolet"
  "Coupe"
  "Coupe Quattro"
  "Q3"
  "Q5"
  "Q7"
  "RS3"
  "RS4"
  "RS5"
  "RS6"
  "S3"
  "S4"
  "S5"
  "S6"
  "S8"
  "TT"
  "TT Quattro"
  "V8 Quattro"],
 "Austin" ["Healey Sprite" "Mini" "Mini Cooper" "Princess"],
 "BMW"
 ["116ED"
  "116i"
  "118d"
  "118i "
  "120d"
  "120i"
  "123D"
  "125D"
  "125i"
  "128i"
  "130i"
  "135i"
  "1600-2"
  "1M Coupe"
  "2002"
  "2002tii"
  "316"
  "318d"
  "318i"
  "318is"
  "318ti"
  "320d"
  "320i"
  "323Ci"
  "323i"
  "323is"
  "325"
  "325Ci"
  "325d"
  "325e"
  "325es"
  "325i"
  "325is"
  "325iX"
  "325xi"
  "328"
  "328Ci"
  "328i"
  "328is"
  "328xi"
  "330Ci"
  "330d"
  "330i"
  "330xi"
  "335"
  "335d"
  "335i"
  "335xi"
  "520d"
  "520i"
  "523i"
  "524td"
  "525d"
  "525i"
  "525iT"
  "525tds"
  "528e"
  "528i"
  "528xi"
  "530d"
  "530i"
  "530xi"
  "535d"
  "535i"
  "535is"
  "535xi"
  "540i"
  "545i"
  "550i"
  "635CSi"
  "635d"
  "650i"
  "730d"
  "735i"
  "735iL"
  "740i"
  "740iL"
  "745i"
  "745Li"
  "750i"
  "750iL"
  "750Li"
  "760Li"
  "840Ci"
  "850CSi"
  "850i"
  "Alpina D3 BiTurbo"
  "Bavaria"
  "M3"
  "M5"
  "M535i"
  "M6"
  "X1"
  "X3"
  "X5"
  "X6"
  "Z3"
  "Z4"],
 "Bentley" ["Continental"],
 "Buick"
 ["Centurion"
  "Century"
  "Electra"
  "Enclave"
  "LaCrosse"
  "LeSabre"
  "Lucerne"
  "Park Avenue"
  "Rainier"
  "Regal"
  "Rendezvous"
  "Riviera"
  "Roadmaster"
  "Skyhawk"
  "Skylark"
  "Special"
  "Terraza"],
 "Cadillac"
 ["Brougham"
  "Catera"
  "CTS"
  "DeVille"
  "DTS"
  "Eldorado"
  "Escalade"
  "Fleetwood"
  "Seville"
  "SRX"
  "STS"
  "XLR"],
 "Caterham" ["Roadsport SV"],
 "Chery" ["Eastar" "QQ"],
 "Chevrolet"
 ["3100"
  "Astra"
  "Astro"
  "Avalanche"
  "Avalanche 1500"
  "Avalanche 2500"
  "Aveo"
  "Aveo5"
  "Bel Air"
  "Beretta"
  "Biscayne"
  "Blazer"
  "C10"
  "C10 Pickup"
  "C1500"
  "C1500 Suburban"
  "C20"
  "C20 Panel"
  "C20 Pickup"
  "C20 Suburban"
  "C2500"
  "C2500 Suburban"
  "C30"
  "C3500"
  "Camaro"
  "Caprice"
  "Cavalier"
  "Celebrity"
  "Celta"
  "Chevelle"
  "Chevy II"
  "Cheyenne"
  "Citation"
  "Citation II"
  "Classic"
  "Cobalt"
  "Colorado"
  "Commercial Chassis"
  "Corsica"
  "Corvair"
  "Corvette"
  "Cruze"
  "El Camino"
  "Equinox"
  "Express 1500"
  "Express 2500"
  "Express 3500"
  "G10"
  "G20"
  "G20 Van"
  "HHR"
  "Impala"
  "K10"
  "K10 Pickup"
  "K10 Suburban"
  "K1500"
  "K1500 Suburban"
  "K20"
  "K20 Pickup"
  "K2500"
  "K2500 Suburban"
  "K3500"
  "K5 Blazer"
  "Kalos"
  "Lumina"
  "Malibu"
  "Metro"
  "Monte Carlo"
  "Nova"
  "Optra"
  "Orlando"
  "P30"
  "Pickup"
  "Prizm"
  "R10"
  "S10"
  "S10 Blazer"
  "Silverado 1500"
  "Silverado 1500 HD"
  "Silverado 2500"
  "Silverado 2500 HD"
  "Silverado 3500"
  "Silverado 3500 HD"
  "Sonic"
  "Spark"
  "Spectrum"
  "Sprint"
  "SSR"
  "Suburban"
  "Suburban 1500"
  "Suburban 2500"
  "Tahoe"
  "Tracker"
  "Trailblazer"
  "Traverse"
  "Truck"
  "Uplander"
  "V1500 Suburban"
  "V20"
  "Vega"
  "Venture"],
 "Chrysler"
 ["200"
  "300"
  "300M"
  "300C"
  "Aspen"
  "Cirrus"
  "Concorde"
  "Conquest"
  "Cordoba"
  "Crossfire"
  "Fifth Avenue"
  "Grand Voyager"
  "Imperial"
  "Laser"
  "LeBaron"
  "LHS"
  "New Yorker"
  "Pacifica"
  "Prowler"
  "PT Cruiser"
  "Sebring"
  "Town & Country"
  "Voyager"],
 "Citroen"
 ["2CV"
  "AX"
  "Berlingo"
  "C1"
  "C2"
  "C3"
  "C4"
  "C4 Picasso"
  "C5"
  "CX"
  "DS19"
  "DS21"
  "GSA"
  "Jumpy"
  "Saxo"
  "SM"
  "Xantia"
  "Xsara"
  "ZX"],
 "Dacia" ["1310"],
 "Daewoo" ["Lanos" "Leganza" "Nubira"],
 "Daihatsu" ["Charade" "Copen" "HiJet" "Rocky" "Storia" "Terios"],
 "Datsun" ["240Z" "280Z" "620"],
 "DeLorean" ["DMC 12"],
 "Dodge"
 ["Aries"
  "Avenger"
  "B1500"
  "B200"
  "B250"
  "B2500"
  "B350"
  "Caliber"
  "Caravan"
  "Challenger"
  "Charger"
  "Colt"
  "D100"
  "D150"
  "D250"
  "D350"
  "D50"
  "Dakota"
  "Dart"
  "Daytona"
  "Diplomat"
  "Durango"
  "Dynasty"
  "Grand Caravan"
  "Intrepid"
  "Journey"
  "Lancer"
  "Magnum"
  "Monaco"
  "Neon"
  "Nitro"
  "Omni"
  "Polara"
  "Power Wagon"
  "Raider"
  "Ram 1500"
  "Ram 2500"
  "Ram 3500"
  "Ram 50"
  "Ramcharger"
  "Shadow"
  "Spirit"
  "Sprinter 2500"
  "Sprinter 3500"
  "Stealth"
  "Stratus"
  "Truck"
  "Viper"
  "W150"
  "W250"
  "W350"],
 "Eagle" ["Premier" "Summit" "Talon"],
 "Edsel" ["Ranger"],
 "FSO" ["Polonez CE"],
 "Ferrari" ["Enzo" "F355 Berlinetta"],
 "Fiat"
 ["126 BIS"
  "127"
  "500"
  "500C"
  "barchetta"
  "Brava"
  "Bravo"
  "Cinquecento"
  "Coupe 20VT"
  "Croma"
  "Doblo"
  "Ducato"
  "Grande Punto"
  "Idea"
  "Marea"
  "Marea Weekend"
  "Multipla"
  "Palio"
  "Panda"
  "Panda 4x4"
  "Punto"
  "Punto Evo"
  "Qubo"
  "Seicento"
  "Stilo"
  "Strada"
  "Tipo"
  "Ulysse"
  "Uno"
  "Uno Fire 1100"
  "Uno Mille Fire Flex"
  "X-19"],
 "Ford"
 ["Aerostar"
  "Aspire"
  "Bantam"
  "Bronco"
  "Bronco II"
  "Capri"
  "Club Wagon"
  "C-Max"
  "C-Max Energi"
  "Contour"
  "Contour SVT"
  "Cortina"
  "Courier"
  "Crown Victoria"
  "Customline"
  "E-100 Econoline"
  "E-150"
  "E-150 Club Wagon"
  "E-150 Econoline"
  "E-150 Econoline Club Wagon"
  "E-250"
  "E-250 Econoline"
  "E-250 Super Duty"
  "E-350"
  "E-350 Club Wagon"
  "E-350 Econoline"
  "E-350 Econoline Club Wagon"
  "E-350 Super Duty"
  "E-450 Econoline Super Duty"
  "E-450 Econoline Super Duty Stripped"
  "E-450 Super Duty"
  "E-450 Super Duty Stripped Chassis"
  "Econoline"
  "Econoline Super Duty"
  "Edge"
  "Escape"
  "Escape Hybrid"
  "Escort"
  "Excursion"
  "Expedition"
  "Explorer"
  "Explorer Sport"
  "Explorer Sport Trac"
  "F Series"
  "F Super Duty"
  "F-100"
  "F-100 Pickup"
  "F-150"
  "F-150 Heritage"
  "F-250"
  "F-250 Pickup"
  "F-250 Super Duty"
  "F-350"
  "F-350 Pickup"
  "F-350 Super Duty"
  "F-450 Super Duty"
  "F53"
  "F-550 Super Duty"
  "Fairlane"
  "Fairmont"
  "Falcon"
  "Festiva"
  "Fiesta"
  "Figo"
  "Five Hundred"
  "Flex"
  "Focus"
  "Focus SVT"
  "Ford"
  "Freda"
  "Freestar"
  "Freestyle"
  "Fusion"
  "Galaxie"
  "Galaxy"
  "Gran Torino"
  "Granada"
  "Grand C-Max"
  "Ka"
  "Kuga"
  "LTD"
  "LTD Crown Victoria"
  "Maverick"
  "Model A"
  "Mondeo"
  "Mustang"
  "Mustang II"
  "Pickup"
  "Probe"
  "Puma"
  "Ranchero"
  "Ranger"
  "S-MAX"
  "Taunus"
  "Taurus"
  "Taurus X"
  "Tempo"
  "Thunderbird"
  "Transit"
  "Windstar"
  "ZX2"],
 "GMC"
 ["1500 Series"
  "2500 Series"
  "Acadia"
  "C15/C1500 Pickup"
  "C1500"
  "C1500 Suburban"
  "C2500"
  "C2500 Suburban"
  "C3500"
  "Canyon"
  "Envoy"
  "G25/G2500 Van"
  "G2500"
  "G35"
  "G3500"
  "Jimmy"
  "K15"
  "K15/K1500 Pickup"
  "K1500"
  "K1500 Suburban"
  "K25/K2500 Pickup"
  "K2500"
  "K2500 Suburban"
  "K3500"
  "P152"
  "S15"
  "S15 Jimmy"
  "Safari"
  "Savana 1500"
  "Savana 2500"
  "Savana 3500"
  "Sierra 1500"
  "Sierra 2500"
  "Sierra 2500 HD"
  "Sierra 3500"
  "Sierra 3500 HD"
  "Sierra C3"
  "Sierra Denali"
  "Sonoma"
  "Terrain"
  "Truck"
  "Typhoon"
  "V1500 Suburban"
  "V3500"
  "Yukon"
  "Yukon Denali"
  "Yukon Denali XL"
  "Yukon XL 1500"
  "Yukon XL 2500"],
 "Geo" ["Metro" "Prizm" "Storm" "Tracker"],
 "Great Wall" ["Hover" "Steed 5"],
 "Holden"
 ["Astra"
  "Barina"
  "Colorado"
  "Commodore"
  "Maloo"
  "VE Commodore"
  "Vectra"
  "VT Commodore"
  "VX Commodore"],
 "Honda"
 ["Accord"
  "City ZX"
  "Civic"
  "Civic del Sol"
  "Civic Si"
  "Civic SIR-II"
  "Crosstour"
  "CR-V"
  "CRX"
  "CR-Z"
  "Domani"
  "Element"
  "Fit"
  "Freed"
  "FR-V"
  "Insight"
  "Integra"
  "Jazz"
  "Legend"
  "Odyssey"
  "Passport"
  "Pilot"
  "Prelude"
  "Ridgeline"
  "S2000"
  "S600"
  "Wagovan"],
 "Hummer" ["H1" "H2" "H3"],
 "Hyundai"
 ["Accent"
  "Atos"
  "Azera"
  "Elantra"
  "Elantra Touring"
  "Entourage"
  "Excel"
  "Genesis"
  "i10"
  "i10 Comfort"
  "i20"
  "i30"
  "ix20"
  "ix35"
  "Matrix"
  "Santa Fe"
  "Santro"
  "Scoupe"
  "Sonata"
  "Terracan"
  "Tiburon"
  "Tucson"
  "Veloster"
  "Veloster Turbo"
  "Veracruz"
  "XG350"],
 "Infiniti"
 ["EX35"
  "FX35"
  "FX45"
  "FX50"
  "G20"
  "G35"
  "G37"
  "I30"
  "I35"
  "J30"
  "M35"
  "M45"
  "Q45"
  "QX4"
  "QX56"],
 "Intermeccanica" ["Speedster"],
 "International" ["100" "R110" "Scout II"],
 "Isuzu"
 ["Amigo"
  "Ascender"
  "Bighorn"
  "Frontier"
  "Hombre"
  "i-280"
  "i-290"
  "I-Mark"
  "NPR"
  "Oasis"
  "Pickup"
  "Rodeo"
  "Trooper"
  "VehiCROSS"],
 "Jaguar"
 ["S-Type"
  "Super V8"
  "Vanden Plas"
  "XF"
  "XFR"
  "XFR-S"
  "XJ"
  "XJ12"
  "XJ6"
  "XJ8"
  "XJR"
  "XJS"
  "XK"
  "XK8"
  "XKE"
  "XKR"
  "X-Type"],
 "Jeep"
 ["Cherokee"
  "CJ5"
  "CJ7"
  "Comanche"
  "Commander"
  "Compass"
  "Grand Cherokee"
  "Grand Wagoneer"
  "J10"
  "J20"
  "Jeepster"
  "Liberty"
  "Patriot"
  "Wagoneer"
  "Wrangler"],
 "Kia"
 ["Amanti"
  "Avella"
  "Carnival"
  "Ceed"
  "Forte"
  "Optima"
  "Picanto"
  "Rio"
  "Rio5"
  "Rondo"
  "Sedona"
  "Sephia"
  "Sorento"
  "Soul"
  "Spectra"
  "Spectra5"
  "Sportage"
  "Venga"],
 "LTI" ["TX4"],
 "Lada" ["Niva" "Riva" "Samara"],
 "Lamborghini" ["Gallardo" "Murcielago"],
 "Lancia" ["Fulvia" "Y" "Ypsilon"],
 "Land Rover"
 ["Defender 110"
  "Defender 90"
  "Discovery"
  "Freelander"
  "LR2"
  "LR3"
  "Range Rover"
  "Range Rover Evoque"
  "Range Rover Sport"
  "Series II"
  "Series III"],
 "Lexus"
 ["CT200h"
  "ES250"
  "ES300"
  "ES300h"
  "ES330"
  "ES350"
  "GS300"
  "GS350"
  "GS400"
  "GS430"
  "GS450h"
  "GS460"
  "GX470"
  "HS250h"
  "IS F"
  "IS250"
  "IS300"
  "IS350"
  "LS400"
  "LS430"
  "LS460"
  "LX450"
  "LX470"
  "LX570"
  "RX300"
  "RX330"
  "RX350"
  "RX400h"
  "RX450h"
  "SC300"
  "SC400"
  "SC430"],
 "Lincoln"
 ["Aviator"
  "Capri"
  "Continental"
  "LS"
  "Mark LT"
  "Mark VI"
  "Mark VII"
  "Mark VIII"
  "MKS"
  "MKT"
  "MKX"
  "MKZ"
  "Navigator"
  "Town Car"
  "Zephyr"],
 "Lotus" ["Elise" "Esprit" "Exige" "Seven"],
 "MG"
 ["1100" "6 Magnette" "MGA" "MGB" "Midget" "TF" "ZR" "ZR\n" "ZS" "ZT"],
 "Mahindra" ["Thar"],
 "Maserati" ["3200GT" "Coupe" "Quattroporte" "Spyder"],
 "Mazda"
 ["121"
  "2"
  "3"
  "323"
  "5"
  "6"
  "626"
  "929"
  "B2000"
  "B2200"
  "B2300"
  "B2500"
  "B2600"
  "B3000"
  "B4000"
  "Bongo"
  "Cosmo"
  "CX-5"
  "CX-7"
  "CX-9"
  "Demio"
  "GLC"
  "Lantis"
  "Millenia"
  "MP3"
  "MPV"
  "MX-3"
  "MX-5 Miata"
  "MX-6"
  "Navajo"
  "Premacy"
  "Protege"
  "Protege5"
  "RX-7"
  "RX-8"
  "Speed 3"
  "Speed6"
  "Tribute"],
 "Mercedes-Benz"
 ["130 H"
  "180C"
  "190"
  "190D"
  "190E"
  "200"
  "200D"
  "220"
  "220D"
  "230"
  "230S"
  "240D"
  "250SE"
  "280C"
  "280CE"
  "280E"
  "280S"
  "280SL"
  "300CD"
  "300CE"
  "300D"
  "300E"
  "300SD"
  "300SDL"
  "300SE"
  "300SEL"
  "300SL"
  "300TD"
  "300TE"
  "350SD"
  "350SDL"
  "400E"
  "420SEL"
  "450SL"
  "450SLC"
  "500E"
  "500SL"
  "560SEC"
  "560SEL"
  "560SL"
  "A Class"
  "B-class"
  "C Class"
  "C220"
  "C230"
  "C240"
  "C250"
  "C280"
  "C300"
  "C32 AMG"
  "C320"
  "C350"
  "C36 AMG"
  "C43 AMG"
  "C55 AMG"
  "C63 AMG"
  "CL55 AMG"
  "CL600"
  "CLK320"
  "CLK350"
  "CLK430"
  "CLK500"
  "CLK55 AMG"
  "CLS500"
  "CLS550"
  "CLS63 AMG"
  "E220 CDI"
  "E280 CDI"
  "E300"
  "E300D"
  "E320"
  "E350"
  "E420"
  "E430"
  "E500"
  "E55 AMG"
  "E550"
  "E63 AMG"
  "G55 AMG"
  "GL320"
  "GL450"
  "ML320"
  "ML350"
  "ML430"
  "ML500"
  "ML55 AMG"
  "ML550"
  "R320"
  "R350"
  "S320"
  "S350"
  "S430"
  "S500"
  "S550"
  "S600"
  "SL320"
  "SL500"
  "SL55 AMG"
  "SL550"
  "SL600"
  "SLK230"
  "SLK250"
  "SLK280"
  "SLK320"
  "SLK350"
  "SLK55 AMG"
  "SLR McLaren"
  "sprinter"],
 "Mercury"
 ["Capri"
  "Cougar"
  "Grand Marquis"
  "Marauder"
  "Mariner"
  "Marquis"
  "Milan"
  "Montego"
  "Monterey"
  "Mountaineer"
  "Mystique"
  "Sable"
  "Topaz"
  "Tracer"
  "Villager"],
 "Microcar" ["MC1"],
 "Mini"
 ["Clubman"
  "Clubman D"
  "Clubman JCW"
  "Clubman S"
  "Cooper"
  "Cooper S"
  "Countryman"
  "JCW"
  "Morris Mini-Minor 1000"],
 "Mitsubishi"
 ["3000GT"
  "Canter"
  "Chariot Grandis"
  "Charisma"
  "Delica"
  "Diamante"
  "Eclipse"
  "Endeavor"
  "Expo"
  "Expo LRV"
  "FTO"
  "Galant"
  "Grandis"
  "L200"
  "Lancer"
  "Lancer Evolution"
  "Legnum"
  "Mighty Max"
  "Mirage"
  "Montero"
  "Montero Sport"
  "Outlander"
  "Outlander Sport"
  "Pajero"
  "Raider"
  "RVR "
  "Sigma"
  "space star"
  "Space Wagon"
  "Starion"
  "Triton"
  "Van"],
 "Morris" ["Minor"],
 "Naza" ["Ria"],
 "Nissan"
 ["1600"
  "200SX"
  "210"
  "240SX"
  "280Z"
  "280ZX"
  "300ZX"
  "310"
  "350Z"
  "370Z"
  "510"
  "520 Pickup"
  "720"
  "Almera"
  "Altima"
  "Armada"
  "Bluebird"
  "Cube"
  "D21"
  "Frontier"
  "Grand Livina"
  "GT-R"
  "Juke"
  "Latio"
  "Livina"
  "Lucino"
  "Maxima"
  "Micra"
  "Murano"
  "Note"
  "NP200"
  "NX"
  "Pao"
  "Pathfinder"
  "Patrol"
  "Pickup"
  "Pintara"
  "Prairie"
  "Primera"
  "Pulsar"
  "Pulsar NX"
  "Qashqai"
  "Quest"
  "Rogue"
  "Sentra"
  "Silvia"
  "Skyline"
  "Stanza"
  "Sunny"
  "Teena"
  "Terrano II"
  "Titan"
  "Versa"
  "Xterra"
  "X-Trail"],
 "Oldsmobile"
 ["442"
  "88"
  "98"
  "Achieva"
  "Alero"
  "Aurora"
  "Bravada"
  "Cutlass"
  "Cutlass Ciera"
  "Cutlass Cruiser"
  "Cutlass Supreme"
  "Delta 88"
  "Dynamic"
  "Intrigue"
  "LSS"
  "Regency"
  "Silhouette"
  "Toronado"
  "Vista Cruiser"],
 "Opel"
 ["Agila"
  "Astra"
  "Astra G"
  "Astra H"
  "Astra H GTC Sport"
  "Corsa"
  "Corsa B"
  "Corsa C"
  "Insignia"
  "Kadett"
  "Manta"
  "Sintra "
  "Vectra A "
  "Vectra B"
  "Vectra C"
  "Vivaro"
  "Omega"],
 "Perodua" ["Kancil" "Kembara" "MyVi" "Viva"],
 "Peugeot"
 ["1007"
  "106"
  "107"
  "205"
  "206"
  "207"
  "306"
  "307"
  "308"
  "405"
  "406"
  "407"
  "5008"
  "504"
  "505"
  "604"
  "607"
  "Expert"
  "Partner"
  "RCZ"],
 "Plymouth"
 ["Acclaim"
  "Barracuda"
  "Breeze"
  "Colt"
  "Duster"
  "Fury III"
  "Grand Voyager"
  "Horizon"
  "Laser"
  "Neon"
  "Satellite"
  "Sundance"
  "Valiant"
  "Voyager"],
 "Pontiac"
 ["6000"
  "Aztek"
  "Bonneville"
  "Fiero"
  "Firebird"
  "G5"
  "G6"
  "G8"
  "Grand Am"
  "Grand Prix"
  "GTO"
  "LeMans"
  "Montana"
  "Phoenix"
  "Pursuit"
  "Solstice"
  "Sunbird"
  "Sunfire"
  "Torrent"
  "Trans Sport"
  "Ventura"
  "Vibe"],
 "Porsche"
 ["356A"
  "911"
  "912"
  "914"
  "924"
  "928"
  "930"
  "944"
  "968"
  "Boxster"
  "Boxster Spyder"
  "Carrera"
  "Cayenne"
  "Cayman"],
 "Proton"
 ["Exora"
  "Inspira"
  "Perdana"
  "Persona"
  "Preve"
  "Saga"
  "Savvy"
  "Tiara"
  "Waja"],
 "Reliant" ["Fox" "Kitten" "Rebel" "Rialto" "Robin"],
 "Renault"
 ["4CV"
  "Clio"
  "Espace"
  "Espace IV"
  "Fluence"
  "Grand Scenic"
  "Kangoo"
  "Laguna"
  "Laguna Break"
  "Logan"
  "Master"
  "Megane"
  "R5"
  "Symbol"
  "Traffic"
  "Twingo"],
 "Rolls Royce" ["Phantom" "Silver Spur"],
 "Rover"
 ["100"
  "2000"
  "214"
  "25"
  "3500"
  "400 "
  "45"
  "620Ti"
  "75"
  "Maestro"
  "Metro"],
 "Saab" ["900" "9000" "9-2X" "93" "9-3" "9-3 Viggen" "9-5" "96" "9-7X" "9-4X"],
 "Saleen" ["Mustang S351"],
 "Samsung" ["SM5"],
 "Saturn"
 ["Astra"
  "Aura"
  "Ion"
  "Ion-1"
  "Ion-2"
  "Ion-3"
  "L100"
  "L200"
  "L300"
  "L300-3"
  "LS"
  "LS1"
  "LS2"
  "LW1"
  "LW200"
  "LW300"
  "Outlook"
  "Relay-2"
  "Relay-3"
  "SC"
  "SC1"
  "SC2"
  "Sky"
  "SL"
  "SL1"
  "SL2"
  "SW1"
  "SW2"
  "Vue"],
 "Scion" ["FR-S" "iQ" "tC" "xA" "xB" "xD"],
 "Seat" ["Alhambra" "cordoba" "Ibiza" "Leon" "Marbella" "Toledo"],
 "Skoda"
 ["Citigo"
  "Fabia"
  "Fabia vRS"
  "Favorit"
  "Felicia"
  "Octavia"
  "Octavia vRS"
  "Rapid"
  "Roomster"
  "Superb"
  "Yeti"],
 "Smart" ["FORFOUR" "Fortwo" "Roadster"],
 "SsangYong" ["Korando" "Musso"],
 "Studebaker" ["Champ" "Lark"],
 "Subaru"
 ["B9 Tribeca"
  "Baja"
  "Brumby"
  "BRZ"
  "DL"
  "Domingo"
  "Fiori"
  "Forester"
  "GL"
  "GL-10"
  "Impreza"
  "Justy"
  "Legacy"
  "Legacy Outback"
  "Loyale"
  "Outback"
  "Sambar"
  "SVX"
  "Tribeca"
  "WRX"
  "XT"
  "XV Crosstrek"],
 "Suzuki"
 ["Aerio"
  "Alto Lxi"
  "Cappuccino"
  "Esteem"
  "Forenza"
  "Grand Vitara"
  "Ignis"
  "Jimny"
  "Liana"
  "Mehran"
  "Reno"
  "Samurai"
  "Sidekick"
  "SJ410"
  "Super Carry"
  "Swift"
  "SX4"
  "Verona"
  "Vitara"
  "XL-7"
  "Zen"],
 "TVR" ["Cerbera" "Tuscan"],
 "Talbot" ["Express"],
 "Tata" ["Indica" "Nano" "Vista Aura"],
 "Toyota"
 ["4Runner"
  "Aristo"
  "Aurion"
  "Auris"
  "Avalon"
  "Avanza"
  "Avensis"
  "Aygo"
  "Belta"
  "Caldina"
  "Camry"
  "Carina"
  "Carina E"
  "Celica"
  "Corolla"
  "Corona"
  "Cressida"
  "Echo"
  "Etios"
  "FJ Cruiser"
  "Fortuner"
  "GT86"
  "Highlander"
  "Hilux"
  "Innova"
  "iQ"
  "iQ2"
  "iQ3"
  "Land Cruiser"
  "Liteace"
  "Mark II"
  "Matrix"
  "MR2"
  "MR2 Spyder"
  "Paseo"
  "Pickup"
  "Prado"
  "Previa"
  "Prius"
  "Prius C"
  "Prius Plug-in"
  "Prius v"
  "Quantum"
  "RAV4"
  "Sequoia"
  "Sienna"
  "Solara"
  "Spacio"
  "Starlet"
  "Supra"
  "T100"
  "Tacoma"
  "Tercel"
  "Tundra"
  "Unser"
  "Van"
  "Venza"
  "Verso"
  "Verso S"
  "VIOS"
  "Vitz"
  "Wish"
  "Yaris"
  "Yaris Hybrid"
  "Yaris TS"],
 "Triumph" ["Dolomite" "Spitfire" "Stag" "TR7" "TR8"],
 "VEB Sachsenring" ["Trabant 601"],
 "Vauxhall"
 ["Agila"
  "Astra"
  "Corsa"
  "Frontera"
  "Insignia"
  "Mariva"
  "Movano"
  "Nova"
  "Vectra"
  "Zafira"],
 "Volkswagen"
 ["Amarok"
  "Beetle"
  "Bora"
  "Cabrio"
  "Cabriolet"
  "Caddy"
  "Campmobile"
  "CC"
  "Corrado"
  "Crafter"
  "Cross Fox"
  "Dasher"
  "Eos"
  "EuroVan"
  "Fox"
  "GLI"
  "Gol"
  "Gol Country"
  "Golf"
  "Golf Plus"
  "Golf R"
  "GTI"
  "Jetta"
  "Karmann Ghia"
  "Kombi"
  "Lupo"
  "Passat"
  "Phaeton"
  "Polo"
  "Polo Vivo"
  "Quantum"
  "R32"
  "Rabbit"
  "Rabbit Convertible"
  "Routan"
  "Santana"
  "Scirocco"
  "Sharan"
  "Squareback"
  "Super Beetle"
  "Tiguan"
  "Touareg"
  "Touran"
  "Transporter"
  "Up"
  "Vanagon"],
 "Volvo"
 ["122"
  "144"
  "145"
  "164"
  "1800"
  "240"
  "242"
  "244"
  "245"
  "265"
  "340"
  "440"
  "740"
  "745"
  "760"
  "780"
  "850"
  "940"
  "960"
  "C30"
  "C70"
  "S40"
  "S60"
  "S70"
  "S80"
  "S90"
  "V40"
  "V50"
  "V60"
  "V70"
  "V90"
  "XC60"
  "XC70"
  "XC90"],
 "Wolseley" ["4-44"],
 "Zastava" ["10"]]))
