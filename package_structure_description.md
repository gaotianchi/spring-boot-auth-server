# Package Structure Description

#### com.gaotianchi.auth.infrastructure

Infrastructure provides the infrastructure for MySQL table operations, including adding rows, adding rows in batches,
deleting rows, deleting rows in batches, updating rows, updating or adding rows in batches, getting rows, and getting
rows in pages.

Infrastructure has three sub-packages, namely entity, dao, and service. Entity defines the mapping from the database
table structure to the entity class, dao and the XML file under resource/mapper/base/ define the specific way to operate
the database, and service defines the basic operation method interface mentioned above.

To avoid spelling errors, use code templates to generate code for this module whenever possible. Use lombok annotations
as much as possible to enhance the readability of the code.

##### Entity class code specification

```java
package com.gaotianchi.auth.infrastructure.entity;

import lombok.Builder;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * Entity
 *
 * @author author name
 * @since datetime
 */
@Data
@Builder
public class Entity implements Serializable {

    @Serial
    private static final long serialVersionUID = 938728593173186031L;

    private Integer id;
    ...
}
```

##### Dao layer code specification

In desiging DAO interface classes, the naming coventions should follow these rules to ensure semantic clarity and
readablitiy.

1. Interface naming: The name of DAO interface clases should be based on the entity class with the suffix `BaseDao`,
   such as `EntityBaseDao`.
2. Method naming:

    1. Method name shoud align with SQL statement keywords, Common verbs
       include: `insert`, `delete`, `update`, `select`.
    2. The verbs should be followd by the object, typically the entity class name, e.g. `insertEntity`.
3. Modifiers for verbs:

    1. Use the format `By + FieldName` to specify query conditions, e.g, `selectEntityById`, `updateEntityByName`.
    2. For batch operations, add `InBatches` in method name,
       e.g, `insertentitiesInBatches`, `deleteEntitiesInBatchesByIds`.
    3. For paginated queries, add `ByPage` to the method name, e.g, `selectEntitiesByPage`.
4. Paging parameters: Use Pageable object pass paramters related to paging, ensuring clarity and flexbility in interface
   methods.

```java
package com.gaotianchi.auth.infrastructure.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Dao
 *
 * @author author name
 * @since datetime
 */
@Mapper
public interface EntityBaseDao {

    int insertEntity(Entity entity);

    int insertEntitysBatch(@Param("entities") List<Entity> entities);

    int deleteEntityById(Integer id);

    int deleteEntitiesInBatchesByIds(@Param("ids") List<Integer> ids);

    int updateEntityById(Entity entity);

    int insertOrUpdateExistingEntitysInBatches(@Param("entities") List<Entity> entities);

    Entity selectEntityById(Integer id);

    long CountByEntity(Entity entity);

    List<Entity> selectEntitiesByPage(Entity entity, @Param("pageable") Pageable pageable);
}
```

##### Service layer code specification

In the service layer, interface classes are located in the root directory, while the implementation classes for each
interface are stored in the subpackage `impl`. The naming convention for interface is typically the entity name followed
by `BaseService`,whild the implementation classes ard named by appending `Impl`, i.e.,`BaseServiceImpl`.

Each service interface has a one-to-one correspondence with the interfaces in the DAO layer (Data Access Object). The
methods in the service interface generally correspond directly to those in the DAO interface, except for
the `countByEntity` method.

In terms of naming and method descriptions, there is a distincthion between service layer and the DAO layer. The methods
in the service layer are named in the way that is closer to everyday language, better reflecting the operations and
functionalities in the business layer.

```java
package com.gaotianchi.auth.infrastructure.service;

import com.gaotianchi.auth.infrastructure.entity.Entity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * Entity
 * 
 * @author author name
 * @since datetime
 */
public interface EntityBaseService {

    void addNewEntity(Entity entity);

    void addNewEntitiesInBatches(List<Entity> entities);

    void removeEntityById(Integer id);

    void removeEntitiesInBatchesByIds(List<Integer> ids);

    void updateEntityDetailsById(Entity entity);

    void addNewOrUpdateExistingEntitiesInBatches(List<Entity> entities);

    Role getEntityById(Integer id);

    Page<Entity> getEntitiesByPage(Entity entity, PageRequest pageRequest);
}

```
