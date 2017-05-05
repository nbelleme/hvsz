package io.resourcepool.hvsz.api;

//@RestController
public class RestApiController {
//
//    @Autowired
//    private DaoMapDb dao;
//
//    @Autowired
//    private GameSettingsService confService;
//
//    @Autowired
//    private ZombieService zService;
//
//    @Autowired
//    private HumanService humanService;
//
//    @Autowired
//    private ZoneService zoneService;
//
//    @Autowired
//    private StatusService statusService;
//
//    private static final Logger LOGGER = LoggerFactory.getLogger(DaoMapDb.class);
//
//
//    /**
//     * Get all games.
//     *
//     * @return List all games
//     */
//    @RequestMapping(value = "/api/game", method = RequestMethod.GET)
//    @ResponseBody
//    public List<Game> getAllGames() {
//        return dao.getAll();
//    }
//
//    /**
//     * Init a new game with default config and start it.
//     *
//     * @return List all games
//     */
//    @RequestMapping(value = "/api/init", method = RequestMethod.GET)
//    @ResponseBody
//    public List<Game> init() {
//        newGame("1");
//        setGameConfig(1L, "30", "0", "30", "10", "2", "40", "2", "180");
//        GameSettings conf = confService.get(1L);
//
//        Status status = GenericBuilder.of(Status::new)
//            .with(Status::setHumanPlayers, conf.getHumanTickets())
//            .with(Status::setZombiePlayers, conf.getNbZombie())
//            .with(Status::setCurrentHumansOnField, 0)
//            .with(Status::setRemainingTime, conf.getGameDuration())
//            .with(Status::setRemainingHumanTickets, conf.getNbSafezoneLifes())
//            .with(Status::setStarted, true)
//            .build();
//
//        statusService.add(status, 1L);
//
//        ArrayList<FoodSupply> foodSupplies = new ArrayList<>();
//        int nbSupplyZones = conf.getNbFoodSupplyZones();
//        int nbSupplyResources = conf.getNbFoodSupplies();
//        for (int i = 0; i < conf.getNbFoodSupplyZones(); i++) {
//            foodSupplies.add(new FoodSupply(i, nbSupplyResources / nbSupplyZones));
//        }
//        zoneService.setSupplyZones(foodSupplies);
//
//        ArrayList<SafeZone> safeZones = new ArrayList<>();
//        int nbSafeZones = conf.getNbSafeZones();
//        for (int i = 0; i < conf.getNbSafeZones(); i++) {
//            safeZones.add(new SafeZone(i, 25, 100));
//        }
//        zoneService.setSafeZones(safeZones);
//
//        return dao.getAll();
//    }
//
//    /**
//     * Create a new game.
//     *
//     * @param key String
//     * @return Game
//     */
//    @RequestMapping(value = "/api/game", method = RequestMethod.POST)
//    @ResponseBody
//    public Game newGame(@RequestParam(value = "key", defaultValue = "1") String key) {
//        Long newKey = Long.parseLong(key);
//        dao.set(newKey, new Game(newKey)); //use index, not id
//        return dao.get(newKey);
//    }
//
//    /**
//     * Get a game by id.
//     *
//     * @param id Long
//     * @return Game
//     */
//    @RequestMapping(value = "/api/game/{id}", method = RequestMethod.GET)
//    @ResponseBody
//    public Game getGame(@PathVariable(value = "id") Long id) {
//        return dao.get(id); //use index, not id
//    }
//
//    /**
//     * Get a game by id.
//     *
//     * @param id Long
//     * @return Game
//     */
//    @RequestMapping(value = "/api/game/{id}/start", method = RequestMethod.POST)
//    @ResponseBody
//    public Game startGame(@PathVariable(value = "id") Long id) {
//        LOGGER.debug("Starting game " + id);
//        GameSettings conf = confService.get(id);
//
//        Status status = GenericBuilder.of(Status::new)
//            .with(Status::setHumanPlayers, conf.getHumanTickets())
//            .with(Status::setZombiePlayers, conf.getNbZombie())
//            .with(Status::setCurrentHumansOnField, 0)
//            .with(Status::setRemainingTime, conf.getGameDuration())
//            .with(Status::setRemainingHumanTickets, conf.getNbSafezoneLifes())
//            .with(Status::setStarted, true)
//            .build();
//
//        statusService.add(status, id);
//
//        ArrayList<FoodSupply> foodSupplies = new ArrayList<>();
//        int nbSupplyZones = conf.getNbFoodSupplyZones();
//        int nbSupplyResources = conf.getNbFoodSupplies();
//        for (int i = 0; i < conf.getNbFoodSupplyZones(); i++) {
//            foodSupplies.add(new FoodSupply(i, nbSupplyResources / nbSupplyZones));
//        }
//        zoneService.setSupplyZones(foodSupplies);
//
//        ArrayList<SafeZone> safeZones = new ArrayList<>();
//        int nbSafeZones = conf.getNbSafeZones();
//        for (int i = 0; i < conf.getNbSafeZones(); i++) {
//            safeZones.add(new SafeZone(i, 25, 100));
//        }
//        zoneService.setSafeZones(safeZones);
//
//        return dao.get(id);
//    }
//
//
//    /**
//     * Kill life by token.
//     *
//     * @param id    game id.
//     * @param token life token.
//     * @return bool succes?
//     */
//    @RequestMapping(value = "/api/game/{id}/kill/{token}", method = RequestMethod.POST)
//    @ResponseBody
//    public Boolean killHuman(@PathVariable("id") Long id, @PathVariable(value = "token") String token) {
//        return zService.kill(token); //use index, not id
//    }
//
//    /**
//     * Get a new life, return token.
//     *
//     * @param id game id.
//     * @return life token
//     */
//    @RequestMapping(value = "/api/game/{id}/newLife", method = RequestMethod.GET)
//    @ResponseBody
//    public Life newLife(@PathVariable("id") Long id) {
//        return humanService.spawn(); //use index, not id
//    }
//
//    /**
//     * Get resource number by life.
//     */
//    @RequestMapping(value = "/api/game/{id}/life/{lifeToken}/nbResource", method = RequestMethod.GET)
//    @ResponseBody
//    public int getNbResourceByLife(@PathVariable("lifeToken") String lifeToken) {
//        return humanService.getLifeByToken(lifeToken).getNbResources();
//    }
//
//    /**
//     * Set a game config by game id.
//     *
//     * @param id                Long
//     * @param gameDuration      String
//     * @param difficulty        String
//     * @param nbHuman           String
//     * @param nbZombie          String
//     * @param nbSafezone        String
//     * @param nbSafezoneLifes   String
//     * @param nbSupplyZone      String
//     * @param nbSupplyResources String
//     * @return GameConfig
//     */
//    @PostMapping("/api/game/{id}/config")
//    @ResponseBody
//    public GameSettings setGameConfig(@PathVariable(value = "id") Long id,
//                                      @RequestParam(value = "gameDuration", defaultValue = "30") String gameDuration,
//                                      @RequestParam(value = "difficulty", defaultValue = "0") String difficulty,
//                                      @RequestParam(value = "nbHuman", defaultValue = "30") String nbHuman,
//                                      @RequestParam(value = "nbZombie", defaultValue = "10") String nbZombie,
//                                      @RequestParam(value = "nbSafezone", defaultValue = "2") String nbSafezone,
//                                      @RequestParam(value = "nbSafezoneLifes", defaultValue = "40") String nbSafezoneLifes,
//                                      @RequestParam(value = "nbSupplyZone", defaultValue = "2") String nbSupplyZone,
//                                      @RequestParam(value = "nbSupplyResources", defaultValue = "180") String nbSupplyResources) {
//
//        GameSettings conf = GenericBuilder.of(GameSettings::new)
//                .with(GameSettings::setGameDuration, Integer.parseInt(gameDuration))
//                .with(GameSettings::setDifficulty, Integer.parseInt(difficulty))
//                .with(GameSettings::setHumanTickets, Integer.parseInt(nbHuman))
//                .with(GameSettings::setNbZombie, Integer.parseInt(nbZombie))
//                .with(GameSettings::setNbSafeZones, Integer.parseInt(nbSafezone))
//                .with(GameSettings::setNbSafezoneLifes, Integer.parseInt(nbSafezoneLifes))
//                .with(GameSettings::setNbFoodSupplyZones, Integer.parseInt(nbSupplyZone))
//                .with(GameSettings::setNbFoodSupplies, Integer.parseInt(nbSupplyResources))
//                .build();
//
//        confService.add(conf, id);
//
//        return conf;
//    }
//
//
//    /**
//     * Get resource from zone.
//     */
//    @RequestMapping(value = "/api/game/{id}/zone/{zoneId}/get", method = RequestMethod.POST)
//    @ResponseBody
//    public Integer getResource(@PathVariable(value = "id") Long id,
//                               @PathVariable(value = "zoneId") Integer zoneId,
//                               @RequestParam(value = "lifeId") Integer lifeId,
//                               @RequestParam(value = "qte") Integer qte) {
//
//        Integer gotRes = humanService.getResources(zoneId, qte, lifeId);
//
//        return gotRes;
//    }
//
//    /**
//     * Drop resource in zone.
//     */
//    @RequestMapping(value = "/api/game/{id}/zone/{zoneId}/drop", method = RequestMethod.POST)
//    @ResponseBody
//    public Integer dropResource(@PathVariable(value = "id") Long id,
//                                @PathVariable(value = "zoneId") Integer zoneId,
//                                @RequestParam(value = "lifeId") Integer lifeId,
//                                @RequestParam(value = "qte") Integer qte) {
//
//        Integer droppedRes = zoneService.dropById(zoneId, qte, lifeId);
//        return droppedRes;
//    }
//
//    /**
//     * Get zone from id.
//     *
//     * @return
//     */
//    @RequestMapping(value = "/api/game/{id}/zone/{zoneId}", method = RequestMethod.GET)
//    @ResponseBody
//    public Zone getZone(@PathVariable(value = "id") Long id,
//                        @PathVariable(value = "zoneId") Long zoneId) {
//        //Game g = dao.get(id);
//        //TODO Use zoneservice
//        throw new NotImplementedException();
//        //return g.getSafeZones().get(1);
//    }

}
