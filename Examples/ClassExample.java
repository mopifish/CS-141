
// Drake Pickett
// 3.8.23
// Example of Classes in Java


// This is our main class. It shares the same name as our file, and contains our main function.
public class ClassExample {
	public static void main(String[] args){
		/* Now we went through all the trouble of creating that dog class,
		 look how easy it is to make new dogs!*/
		Dog tracy = new Dog("Tracy", "Cocker-Spaniel", "Female", 3);
		Dog baxter = new Dog("Baxter", "Rotweiler", "Male", 10);

		System.out.println("Hi " + tracy.getName());
		tracy.pet();

		System.out.println("This is my pet dog, " + baxter.getName() + ". He is a " + baxter.getBreed());

		/* Now that you've seen an example of classes, think how you can expand 
		 upon classes, and in what ways would they make programming
		 easier? */

	}
}

// This is a separate new class we made
// Another way to think of a class is as a unique object YOU make!
// Strings are another example of an object

// Here is a custom object, or class, we make
// This is our dog object
class Dog {
	// Classes can have data or values stored inside of them.
	// Here is some common information we expect our dog to have
	private String name;
	private final String breed;
	private final String sex; 
	private int age;

	/*--PRIVATE?--
	 When creating a class, its best practice to make the data private
	 This means these variables can ONLY be accessed inside of this class
	 No other class can directly modify our dogs age, instead
	 age is an internal value our dog will modify itself over time
	 (More on that further down)	*/

	/*--FINAL?--
	 Note the use of "final" above. the "final" keyword tells java that
	 a variable is a constant; aka it can never change
	 While a dog can change its name and age over time, 
	 It's breed and sex are constant from birth. Hence, the final keyword */


	/* This is our constructor function. This function is what gets called
	 Automatically whenever we create a new instance of this class
	 (or, for example, whenever we make a new dog!)

	 Consider this the dogs "birth" function. This is what happens 
	 when our lovely little puppy is born!

	 You can identify a constructor method inside of a class 
	 due to the lack of return value. 

	 Notice it has no void, string, int, etc. before the method name*/
	public Dog(String name, String breed, String sex, int age){
		this.name = name;
		this.breed = breed;
		this.sex = sex;
		this.age = age;

		/*--THIS?--
		 the "this." keyword is how we access private data
		 inside of a class. This.name means classes variable called "name"

		 name just means the parameters called "name"*/
	}

	/* Normal dogs have behaviors, so lets give OUR dog some fun behavior!*/

	/* Petting is something we can do to the dog
	 So it will be publicly accessible */
	public static void pet(){
		bark();
	}

	/* Barking isn't something we can do to the dog though
	 Barking is something the dog decides itself. 
	 So this function will be private */
	private static void bark(){
		System.out.println("Bow wow wow!");
	}

	/* SET AND GET?
	 Since all of our information is private, we're gonna need to create methods
	 that both get, and set, this private information.

	 We can skip the setters for our constant information (breed and sex), 
	 but all the data will need getters */

	/* These first two set and get functions seem pretty useless right?
	 it just gets the variable, we could have done that ourselves
	 if it wasn't private!

	 That's because this is still a pretty simple program, but 
	 writing it this way makes it really easy to expand.

	 For example, what if we could only get the name if the 
	 dog trusted you? or if it had a collar?

	 Privatizing this information would allow you to put
	 these constraints on the information.

	 In the same vain, you could write could
	 that only allows you to change the dogs name if
	 your suggested name is in a list of accepted names!*/

	public static void setName(String newName){
		name = newName;
	}

	public static String getName(){
		return name;
	}

	public static String getBreed(){
		return breed;
	}

	public static String getSex(){
		return sex;
	}

	public static void setAge(int newAge){
		age = newAge;
	}

	public static int getAge(){
		return age;
	}

}