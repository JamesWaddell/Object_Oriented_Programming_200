/**************************************************
 * 159.234 Assignment Optional
 * Name: James Waddell
 * ID: 16379344
 **************************************************/
 
/**
 * A class representing a book derived from the base abstract class Item
 */
	class BookShelf {       
		private int mCapacity; //< the capacity
		private int mSize;     //< the size
		private Item mItem[];  //< an array of type Item objects
		
		/**
       * Extends the capacity of the mItem array by incrementing it when = 0, and doubling it when = mSize.
       */
		private void extendCapacity(){
			if(mCapacity == 0){
	         mCapacity++;
	      }else if(mCapacity == mSize){
		      mCapacity*=2;
			}
		}
		
		/**
		 * Constructor- initializes the mItem array to null, and size and capacity to zero  
		 */
		public BookShelf(){
			mItem = null;
			mCapacity = 0;
			mSize = 0;
		}
		
		/**
		 * Converts all the objects contained in the BookShelf into a string format
		 * @return the string 
		 */
		@Override
		public String toString(){
			String output = "";
			for(int i =0; i<mSize; i++){
				 output += mItem[i].toString();
			}
			return output;
		}
		
		/**
       * Returns the current capacity.
       * @return the capacity.
       */
		public int getCapacity(){ return mCapacity; }
		
		/**
       * Returns the current size.
       * @return the size.
       */
		public int getSize(){ return mSize;}
		
		/**
       * Adds an item to the end of the mItem array, extends capacity when necessary, and increments size.
       * @param[in] Item item. the item to be added to the mItem array.
		 * @throw IllegalArgumentException if callNumber already exists
       */
		public void pushBack(Item item){
		   if(find(item.getCallNumber()) != null){
				throw new IllegalArgumentException("Duplicated call number found.");
			}
			extendCapacity();
			//temp array
			Item temp[] = new Item[mCapacity];
			//copy old array into temp array
			for(int i= 0; i<mSize; i++){
		      temp[i] = mItem[i];
	      }
			//new array
			mItem = new Item[mCapacity];
			//copy temp array into new array
			if(temp != null){
			   for(int i = 0; i<mSize; i++){
					mItem[i] = temp[i];
	         }
			}
			mItem[mSize] = item;
			mSize++;
		}
		
		/*Adds an item to the mItem array at a given position, extends capacity when necessary, and increments size.
       * @param[in] Item item. the item to be added to the mItem array.
       * @param[in] int position. the position in mData where the new item is going to be added. 
       * @throw IllegalArgumentException if callNumber already exists or the position is <0.
       */
		public void insert(Item item, int position){
			if(find(item.getCallNumber()) != null){
				throw new IllegalArgumentException("Duplicated call number found.");
			}
	      if(position <0 || position > mSize){
				throw new IllegalArgumentException("Invalid position.");
			}
			if(position == mSize){
		      //push item to back of array
		      this.pushBack(item);
			}else{
				extendCapacity();
				mSize++;
				Item temp[] = new Item[mCapacity];
				for(int i = 0; i<mSize; i++){
					if(i == position){
				      //add item to temporary array
				      temp[i] = item;
			      }else if(i>position){
				      //copy old array at one less than index, into temporary array at index
				      temp[i] = mItem[i-1];
			      }else{
				      //copy old array at index, into temporary array at index
				      temp[i] = mItem[i];
			      }
				}
			   //new array 
		      mItem = new Item[mCapacity];
				//copy temp array into new array
				mItem = temp;
			}
		}
		
		/**
       * Removes an item from the mItem array, decrements size, and returns the item to the caller.
       * @param[in] int index. the position in mItem where the item is going to be removed.
       * @return the item that has been removed from mItem.
       * @throw IllegalArgumentException if the index is <0 or >= mSize;
       */
		public Item remove(int index){
			if(index < 0 || index >= mSize){
		      throw new IllegalArgumentException("Index out of bound.");
	      }
			//temp array
			Item temp[] = new Item[mCapacity];
			//the item that is to be returned
			Item item = null;
	      for(int  i = 0; i<mSize; i++){
		      if(i == index){
		      	item = mItem[i];
		      }else if(i>index){
			      //copys old array at index, into temporary array at 1 less than index
			      temp[i-1] = mItem[i];
		      }else{
			      //copy old array at index, into temporary array at index
			      temp[i] = mItem[i];
		      }
	      }
			//new array 
			mItem = new Item[mCapacity];
			//copy temp array into new array
			mItem = temp;
			mSize--;
			return item;	
		}
		
		/**
		 * Returns an item at position index
		 * @param[in] int index. the index.
		 * @throw IllegalArgumentException if the index is <0 or >= mSize;
		 */
		public Item get(int index){
			if(index < 0 || index >= mSize){
		      throw new IllegalArgumentException("Index out of bound.");
	      }
			return mItem[index];
		}
		
		/**
		 * Locates a item by its call number.
		 * @param[in] String callNumber. the call number. 
		 */
		public Item find(String callNumber){
			for(int i = 0; i<mSize; i++){
				if(mItem[i].getCallNumber().equals(callNumber)){
					return mItem[i];
				}
			}
			return null;
		}
		
		/**
       * Print out names and IDs.
       */
	   public static void printStudentInfo(){
		   System.out.println("**************************************************");
	      System.out.println("* 159.234 Assignment Optional");
	      System.out.println("* Name: James Waddell");
	      System.out.println("* ID: 16379344");
	      System.out.println("**************************************************");
	   }
	}		
	
/**
 * A class representing a book derived from the base abstract class Item
 */
	class Book extends Item{
		private String author;   //< Name of author
		private int numOfPages;  //< Number of pages
		
		/**
		 * Converts a Book object into a string format
		 * @return the string 
		 */
		@Override
		public String toString(){
			return "Book #"+ this.getCallNumber()+", title: \""+this.getTitle()+"\" by "+author+", pages: "+numOfPages+"\n";
		}
			
		/**
       * Constructor for the derived class Book
       * @param[in] _callNumber the call number
       * @param[in] _title the title
       * @param[in] _author the author
       * @param[in] _numPages the number of pages
       */
		public Book(String _callNumber, String _title, String _author, int _numOfPages){
			super(_callNumber, _title);
			author = _author;
			numOfPages = _numOfPages;
		}
	}
	
/**
 * A class representing a journal derived from the base abstract class Item
 */	
	class Journal extends Item{
		private String volume;   //< Volume number of the journal
		private int frequency;   //< Number of times the journal is published each year
		
		/**
		 * Converts a Journal object into a string format
		 * @return the string 
		 */
		@Override
		public String toString(){
			return "Journal #"+this.getCallNumber()+", title: \""+this.getTitle()+"\" volume "+volume+", frequency: "+frequency+"\n";
		}
		
      /**
       * Constructor for the derived class Journal
       * @param[in] _callNumber the call number
       * @param[in] _title the title
       * @param[in] _volume the volume number
       * @param[in] _frequency the number of times the journal is published each year
		 */
		public Journal(String _callNumber, String _title, String _volume, int _frequency){
			super(_callNumber, _title);
			volume = _volume;
			frequency = _frequency;
		}
	}
	
/**
 * A class representing a DVD derived from the base abstact class Item
 */
	class DVD extends Item{
		private String producer;  //< Name of the producer
		
	   /**
		 * Converts a DVD object into a string format
		 * @return the string 
		 */
		@Override
		public String toString(){
			return "DVD #"+this.getCallNumber()+", title: \""+this.getTitle()+"\" produced by "+producer+"\n";
		}
		
		/**
       * Constructor for the derived class DVD
       * @param[in] _callNumber the call number
       * @param[in] _title the title
       * @param[in] _producer the producer 
       */
		public DVD(String _callNumber, String _title, String _producer){
			super(_callNumber, _title);
			producer = _producer;
		}
			
	}

