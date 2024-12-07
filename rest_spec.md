# RESTful Controller Specification

## Class Structure

1. All REST controllers use the `@RestController` annotation
2. All controllers use `@RequestMapping` to define their base path
3. Some controllers use `@Validated` for input validation
4. Controllers follow a consistent dependency injection pattern through constructor injection

## Endpoint Patterns

1. Resource Creation
    - HTTP Method: POST
    - Path: "" (base path)
    - Method naming: `handleCreate{Resource}Request`
    - Batch version: POST to "/batch"

2. Resource Deletion
    - HTTP Method: DELETE
    - Path: "" (base path)
    - Method naming: `handleRemove{Resource}ByIdRequest`
    - Batch version: DELETE to "/batch"

3. Resource Update
    - HTTP Method: PUT
    - Path: "" (base path)
    - Method naming: `handleUpdate{Resource}Request`
    - Batch version: PUT to "/batch"

4. Single Resource Retrieval
    - HTTP Method: GET
    - Path: "info/{id}"
    - Method naming: `handleGet{Resource}ByIdRequest`

5. Resource List Retrieval
    - HTTP Method: GET
    - Path: "info-list"
    - Method naming: `handleGet{Resource}ListRequest`

## Response Pattern

- All endpoints return a `VO<T>` wrapper class
- Common return types include:
    - `VO<String>` for creation operations
    - `VO<Void>` for deletion operations
    - `VO<ResourceVO>` for single resource retrieval
    - `VO<Page<ResourceVO>>` or `VO<Iterable<ResourceVO>>` for list operations

## Naming Conventions

1. Class names should end with "Rest"
2. Base paths should use kebab-case
3. Method names should be in camelCase and start with "handle"
4. Method names should clearly indicate the operation and resource

## Inconsistencies to Fix:

1. RoleRest: Uses "/role" and "/batch" - should be "role" and "batch" (remove leading slashes)
2. RolePermissionRest: Uses "/role-permission" - should be "role-permission" (remove leading slash)
3. Some endpoints have inconsistent batch endpoint paths (with or without leading slash)
4. Some method names don't follow the standard pattern (e.g., "handleAddNew" vs "handleCreate")