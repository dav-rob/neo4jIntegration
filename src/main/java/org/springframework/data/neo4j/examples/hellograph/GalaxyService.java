package org.springframework.data.neo4j.examples.hellograph;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GalaxyService {
	
	@Autowired
	private WorldRepository worldRepository;
	
	public long getNumberOfWorlds() {
		return worldRepository.count();
	}
	
	public World createWorld(String name, int moons) {
		World world = worldRepository.save(new World(name, moons));
		System.out.print(String.format("World namd %s, id[%s]", world.getName(), "" + world.getId()));
		return world;
	}
	
	public Iterable<World> getAllWorlds() {
		return worldRepository.findAll();
	}
	
	public World findWorldById(Long id) {
		return worldRepository.findOne(id);
	}
	
	public World findWorldByName(String name) {
		return worldRepository.findByPropertyValue("name", name);
	}
	
	public Iterable<World> findAllByNumberOfMoons(int numberOfMoons) {
		return worldRepository.findAllByPropertyValue("moons", numberOfMoons);
	}
	
	public Collection<World> makeSomeWorlds() {
		Collection<World> worlds = new ArrayList<World>();
				
		// Solar worlds		
		World mercury = createWorld("Mercury", 0);
		worlds.add(mercury);
		World venus = createWorld("Venus", 0);
		worlds.add(venus);
        
        World earth = createWorld("Earth", 1);        
        World mars = createWorld("Mars", 2);        
        mars.addRocketRouteTo(earth);
        mars.addRocketRouteTo(mercury);
        earth.addRocketRouteTo(venus);
        earth.addRocketRouteTo(mercury);
        worldRepository.save(earth);
        worldRepository.save(mars);
        worlds.add(earth);
        worlds.add(mars);
        
        worlds.add(createWorld("Jupiter", 63));
        worlds.add(createWorld("Saturn", 62));
        worlds.add(createWorld("Uranus", 27));
        worlds.add(createWorld("Neptune", 13));

        // Norse worlds
        worlds.add(createWorld("Alfheimr", 0));
        worlds.add(createWorld("Midgard", 1));
        worlds.add(createWorld("Muspellheim", 2));
        worlds.add(createWorld("Asgard", 63));
        worlds.add(createWorld("Hel", 62));
        
        return worlds;
	}
	
}
