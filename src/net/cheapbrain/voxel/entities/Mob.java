package net.cheapbrain.voxel.entities;

public class Mob extends Entity {
	private String name,customName;
	private float health,air;
	private boolean canPickUp,canDespawn;
	@SuppressWarnings("unused")
	private int[] equipment,equipChances; //0:(Hand)  1:(Feet) 2:(Legs) 3:(Chest) 4:(Head)
	@SuppressWarnings("unused")
	private int[] drops,dropChances;
	
	
	public Mob(int id){
		super(id);
		setEquipment(new int[5],new int[5]); //both equipment and equipchances
		setDrops(new int[5],new int[5]); //both drops and dropchances
	}
	
	public void drop(int id){
		//crea l'entità con l'id passato e la butta a terra
	}
	public void drop(String s){
		//crea l'entità con l'id passato e la butta a terra
	}
	
	public void damage(float power){
		health-=power;
		if(health<=0){
			death();
		}
	}
	
	public void death(){
		
	}
	
	public void setImmortal(){
		health=1000000;
	}
	public boolean isImmortal(){
		return (health>=1000000);
	}
	
	public void setUndrawnable(){
		air=1024;
	}
	public boolean isUndrawnable(){
		return (air>=1024);
	}
	
	public void setName(){
		switch (super.getId()){
		case 0: this.name="Zombie"; break;
		case 1: this.name="Ghost"; break;
		case 2: this.name="Golem"; break;
		case 3: this.name="Lycans"; break;
		case 4: this.name="Minotaur"; break;
		case 5: this.name="Troll"; break;
		case 6: this.name="Ogre"; break;
		case 7: this.name="Goblin"; break;
		case 9: this.name="Ectoplasm"; break;
		case 100: this.name="Chicken"; break;
		case 101: this.name="Sheep"; break;
		case 102: this.name="Pig"; break;
		case 103: this.name="Cow"; break;
		case 104: this.name="Wolf"; break;
		case 105: this.name="Cat"; break;
		case 106: this.name="Goat"; break;
		case 107: this.name="Horse"; break;
		case 109: this.name="Rabbit"; break;
		}
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCustomName() {
		return customName;
	}
	public void setCustomName(String customName) {
		this.customName = customName;
	}
	public float getHealth() {
		return health;
	}
	public void setHealth(float health) {
		this.health = health;
	}

	public boolean isCanPickUp() {
		return canPickUp;
	}

	public void setCanPickUp(boolean canPickUp) {
		this.canPickUp = canPickUp;
	}

	public boolean canDespawn() {
		return canDespawn;
	}

	public void setCanDespawn(boolean canDespawn) {
		this.canDespawn = canDespawn;
	}

	public void setEquipment(int[] equipment,int[] equipChances) {
		this.equipment = equipment;
		this.equipChances = equipChances;
	}

	public void setDrops(int[] drops, int[] dropChances) {
		this.drops = drops;
		this.dropChances = dropChances;
	}
}
