# AdvancedSoftwareDevelop

# Hello dear Dr. Mustafa


We used the Factory design pattern to create different types of file storage services based on StorageType. Since it encapsulates DropboxFileStorage and GoogleDriveFileStorage separately, this design allows for easy extension of new storage types, which creates flexibility and thus leads to scalability by adopting a more abstract factory method for creating The service, which ensures ease of changing it in the future. The responsibility of the code lies in handling and recording specific exceptions, which contributes to system availability and performance monitoring, to further improve the scalability of the system. We also worked on using Apache Log4j in Logging to record all procedures in the system, knowing We spent 4 days learning and trying to run it (uploading to storage locations) from all team members, but from the experience gained from learning, this code was written with excellent logic according to the needs of functional requirements and non-functional requirements.

The code used the strategy design pattern by implementing DeletionStrategy, allowing for interchangeable HardDelete and SoftDelete strategies. The srp was implemented in all classes, separating everything individually so that the code could be added to when there were new requirements. It encapsulated the deletion logic within its own classes, preserving responsibilities. Individuality. Scalability is addressed by handling file deletion, but built-in paths limit flexibility. Accountability is ensured through comprehensive logging of deletions and file operations, providing a clear view of the system. Availability and performance can be enhanced by optimizing file operations and considering asynchronous tasks for large-scale deletions, improving responsiveness and scalability, thus achieving flexibility.

We used the factory design pattern in order to achieve the principle of individual responsibility, so that any information can be added (new requirements, such as new information for the user), and we guarantee that OCP is easily achieved when PDFExporter creates it, and thus LSP is achieved, as each source class deals with creating and recording PDF files, which leads to duplicate instructions. Software. Implementing the factory design pattern to create the PDFExporterFactory will centralize the PDF creation logic and enhance scalability by accommodating future exporter types. Enhancing system scalability can be achieved by using a custom logger class, thus improving system flexibility. Improving system availability and performance may include asynchronous logging to prevent bottlenecks which ultimately improves overall system performance and responsiveness.

We used the template design pattern by defining the PDFZipTemplate with abstract methods for specific file operations, ensuring a uniform workflow while allowing customization, thus fulfilling the dependency inversion principle. Each interface contains only the functionality that is desired, thus ensuring scalability, flexibility, and accountability by encapsulating File processing logic. Additionally, it prioritizes system availability and performance. An integrated logger enhances system accountability by recording actions, enhances system availability by tracking errors, and contributes to system flexibility and scalability.


# Non functional requirements

System Accountability: Code takes responsibility by handling and logging exceptions, ensuring system availability and monitoring performance. Comprehensive logging of operations, including deletions, promotes clear system accountability.

System flexibility: Implementing the plant design pattern and ensuring individual responsibility allows new information to be easily incorporated, ensuring adherence to the principles of open-closed substitution and Leskov substitution. Using a custom logger class improves flexibility while improving scalability.

System Availability: The strategy design pattern separates deletion logic into its own classes, preserving responsibilities and allowing optimized file operations. Asynchronous tasks for large-scale deletions can further enhance availability and responsiveness.

System Performance: A unified workflow for template design style and customization options prioritizes system availability and performance. Comprehensive logger integration contributes to accountability,  scalability, further improving system performance.

System scalability: The system is made such that it can effectively handle the load, such as processing and storage, without affecting the system performance. Use Factory design pattern with StorageType encapsulation. Easily expand to new storage types, which enhances flexibility and scalability. The abstract factory method ensures the ability to adapt to future changes, which enhances scalability.

final project
