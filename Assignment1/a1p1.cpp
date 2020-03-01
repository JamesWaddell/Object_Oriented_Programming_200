/***************************************************
 * 159.234 Assignment 1 Part 1
 * Name: James Waddell
 * ID: 16379344
 ****************************************************/

#include<iostream>
#include<fstream>
#include<cstdio>
#include<string>

using namespace std;

/**
 * A struct repressenting a room with room name, room purpose, maximum time, and a booked state
 *
 */
struct Room{
	string roomName;       ///< name of the room
	string roomPurpose;    ///< purpose of the room
	string maxTime;        ///< max time the room can be booked for
	bool isBooked = false; ///< the state of the room being booked or not, set to false by default
};

/**
 * A struct repressenting a booking with staff name, room purpose, room name, and time booked
 *
 */
struct Booking{
	string staffName;   ///< name of staff
	string roomName;    ///< name of the room 
	string roomPurpose; ///< pupose of the booking
	string timeBooked;  ///< how long the room is booked for
};

/**
 * Print out header
 */
void printHeader();

/**
 * Check to see if there is any room that has not been booked, and print it out
 * @param [in] struct Room R[]. R the array of rooms
 * @param [in] struct Booking B[]. B the array of bookings
 * @param [in] int numOfRooms. numOfRooms the integer, that contains the total number of rooms  
 * @param [in] int numOfBookings. numOfBookings the integer, that contains totalthe number of bookings
 */
void notBooked(struct Room R[10], struct Booking B[10], int numOfRooms, int numOfBookings);

/**
* Check to see if there is any booking with the wrong type of purpose, and print it out
* @param [in] struct Room R[]. R the array of rooms
* @param [in] struct Booking B[]. B the array of bookings
* @param [in] int numOfRooms. numOfRooms the integer, that contains the total number of rooms  
* @param [in] int numOfBookings. numOfBookings the integer, that contains the total number of bookings
*/
void purposeCheck(struct Room R[10], struct Booking B[10], int numOfRooms, int numOfBookings);

/**
 * Check to see if there is any booking that does not specify a room, and print it out
 * @param struct Booking B[]. B the array of bookings
 * @param int numOfBookings. numOfBookings the integer, that contains the total number of bookings
 */
void notSpecified(struct Booking B[10],int numOfBookings);

/**
 * Check to see if there is any booking that is booked for a time longer than the time allowed, and print it out
 * @param [in] struct Room R[]. R the array of rooms
 * @param [in] struct Booking B[]. B the array of bookings
 * @param [in] int numOfRooms. numOfRooms the integer, that contains the total number of rooms  
 * @param [in] int numOfBookings. numOfBookings the integer, that contains the total number of bookings
 */
void timeCheck(struct Room R[10], struct Booking B[10], int numOfRooms, int numOfBookings);

int main(){
	struct Room room[100];
   struct Booking booking[100];
	printHeader();
	ifstream file1, file2;
	file1.open("rooms.txt");
	file2.open("bookings.txt");
	if(!file1){ 
		cerr<<"Error: file rooms.txt is missing. Program terminates"<<endl;;
		return 1;
	}
	if(!file2){
		cerr<<"Error: file bookings.txt is missing. Program terminates"<<endl;
		return 2;
	}
	int numberOfRooms = 0;
	int numberOfBookings = 0;
	file1>>numberOfRooms;
	file2>>numberOfBookings;
	string skip1, skip2;
	getline(file1, skip1);
	getline(file2, skip2);
	for(int i =0; i<numberOfRooms; i++){
		getline(file1, room[i].roomName);
		getline(file1, room[i].roomPurpose);
		getline(file1, room[i].maxTime);
	}
	file1.close();
	for(int i =0; i<numberOfBookings; i++){
	   getline(file2, booking[i].staffName);
		getline(file2, booking[i].roomPurpose);
		getline(file2, booking[i].roomName);
		getline(file2, booking[i].timeBooked);
	}
	file2.close();
	notBooked(room, booking, numberOfRooms, numberOfBookings);
	cout<<endl;
	notSpecified(booking,numberOfBookings);
	cout<<endl;
	purposeCheck(room, booking, numberOfRooms, numberOfBookings);
	cout<<endl;
	timeCheck(room, booking, numberOfRooms, numberOfBookings);
}

/**
 * Print out header
 */
void printHeader(){
	cout<<"***************************************************"<<endl
		 <<"* 159.234 Assingment 1 Part 1"<<endl
		 <<"* Name: James Waddell"<<endl
		 <<"* ID: 16379344"<<endl
	    <<"***************************************************"<<endl;
}

/**
 * Check to see if there is any room that has not been booked, and print it out
 * @param [in] struct Room R[]. R the array of rooms
 * @param [in] struct Booking B[]. B the array of bookings
 * @param [in] int numOfRooms. numOfRooms the integer, that contains the total number of rooms  
 * @param [in] int numOfBookings. numOfBookings the integer, that contains totalthe number of bookings
 */
void notBooked(struct Room R[], struct Booking B[], int numOfRooms, int numOfBookings){
	bool notBookedBrief = true;
	for(int i = 0; i<numOfRooms; i++){
		for(int j = 0; j<numOfBookings; j++){
			if(R[i].roomName.compare(B[j].roomName)==0){
				R[i].isBooked = true;
				break;
			}
		}
		if(R[i].isBooked == false){
			if(notBookedBrief){
				notBookedBrief = false;
				cout<<"The following rooms have not been booked by anyone: "<<endl;
			}
			cout<<"Room: "<<R[i].roomName<<" for "<<R[i].roomPurpose<<" purpose allows maximum "
			    <<R[i].maxTime<<" minutes"<<endl;
		}
	}
}

/**
 * Check to see if there is any booking with the wrong type of purpose, and print it out
 * @param [in] struct Room R[]. R the array of rooms
 * @param [in] struct Booking B[]. B the array of bookings
 * @param [in] int numOfRooms. numOfRooms the integer, that contains the total number of rooms  
 * @param [in] int numOfBookings. numOfBookings the integer, that contains the total number of bookings
 */
void purposeCheck(struct Room R[], struct Booking B[], int numOfRooms, int numOfBookings){ 
	int pos = 0;
	bool purposeBrief = true;
	for(int i = 0; i<numOfBookings; i++){
	   for(int j = 0; j<numOfRooms; j++){
		   if(R[j].roomName.compare(B[i].roomName) == 0){
			   pos = j;
			   break;
			}
		}
	   if((R[pos].roomPurpose.compare(B[i].roomPurpose)!=0)&&(B[i].roomName != "---")){
			if(purposeBrief){
				purposeBrief = false;
				cout<<"The following booking requests have the wrong purposes: "<<endl;
			}
			cout<<"Booking: by "<<B[i].staffName<< " for "<<B[i].roomPurpose<<" at room "
			    <<B[i].roomName<<" for "<<B[i].timeBooked<<" minutes"<<endl;
	   }
	}
}
	
/**
 * Check to see if there is any booking that does not specify a room, and print it out
 * @param struct Booking B[]. B the array of bookings
 * @param int numOfBookings. numOfBookings the integer, that contains the total number of bookings
 */
void notSpecified(struct Booking B[],int numOfBookings){
	bool notSpecifiedBrief = true;
	for(int i =0; i<numOfBookings; i++){
		if(B[i].roomName == "---"){
			if(notSpecifiedBrief){
				notSpecifiedBrief = false;
				cout<<"The following booking requests do not specify a room: "<<endl;
			}
			cout<<"Booking: by "<<B[i].staffName<< " for "<<B[i].roomPurpose
				 <<" at room "<<B[i].roomName<<" for "<<B[i].timeBooked<<" minutes"<<endl;
		}
	}
}

/**
 * Check to see if there is any booking that is booked for a time longer than the time allowed, and print it out
 * @param [in] struct Room R[]. R the array of rooms
 * @param [in] struct Booking B[]. B the array of bookings
 * @param [in] int numOfRooms. numOfRooms the integer, that contains the total number of rooms  
 * @param [in] int numOfBookings. numOfBookings the integer, that contains the total number of bookings
 */
void timeCheck(struct Room R[], struct Booking B[], int numOfRooms, int numOfBookings){
	bool timeBrief = true;
	int pos = 0;
	int j = 0;
	for(int i =0; i<numOfRooms; i++){
	   for(j =0; j<numOfBookings; j++){
			if(R[i].roomName.compare(B[j].roomName) == 0){
				pos  = i;
				break;
			}
		}
		if(R[pos].maxTime < B[j].timeBooked){
			if(timeBrief){
				timeBrief = false;
				cout<<"The following booking requests are over the time limit: "<<endl;
			}
			cout<<"Booking: by "<<B[j].staffName<< " for "<<B[j].roomPurpose<<" at room "
				 <<B[j].roomName<<" for "<<B[j].timeBooked<<" minutes"<<endl;
		}
	}
}
