# key-value-storage
Simple key value storage

1) Clone this repository
2) Run 'gradle bootRun' command
3) Application will be run on 8080 port

REST API:

1) Add new record to the storage:
- REST API - **http://localhost:8080/api/storage**
- HTTP method - **POST**
- Incoming request body(example) : 
{
	"key": "key-1",
	"value": {
		"key": "key-12",
		"innerObject": {
			"key": "key-123"
		}
	}
}

2) Get record by key from the storage:
- REST API - **http://localhost:8080/api/storage?key={key}** ({key} - String type)
- HTTP method **GET**

3) Update new record in the storage:
- REST API - **http://localhost:8080/api/storage**
- HTTP method - **PUT**
- Incoming request body(example) : 
{
   	"key": "key-1",
   	"value": {
   		"key": "key-12-updated",
   		"innerObject": {
   			"key": "key-123-updated"
   		}
   	}
}
   
4) Remove record from the storage:
- REST API - **http://localhost:8080/api/storage**
- HTTP method - **DELETE**
- Incoming request body(example) : 
{
	"key": "key-1",
	"value": {
		"key": "key-12",
		"innerObject": {
			"key": "key-123"
		}
	}
} 