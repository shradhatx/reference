#### Avro
data serialization system
provides: RPC, a container file to store persistent data
This allows construction of generic data processing systems therefore different than thrift - avro does not require code to be generated; Since the schema is present when data is read, considerably less type information need be encoded with data, resulting in smaller serialization size.
Schema is defined in JSON

#### Swift
mac os parallel scripting language




#### AWS Security 
Encrypting data at rest throughout the data lifecycle
EBS volumes - encyption 
RDS - support Transport Data Encryption
S3 buckets - server side encytion (SSE) using AES-256
Instance storage attached to EC2 for temporary block-level storage for buffers, cache,scratch data. By default this is not encrypted. Use linux built in libraries to encrypt data transparently - disk encryption or file encryption.





