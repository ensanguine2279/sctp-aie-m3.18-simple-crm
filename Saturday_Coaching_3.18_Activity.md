# Saturday Coaching — Lesson 3.18 Activity

Work in your existing `simple-crm` project. Nothing new to install, no new entity, no new database.

Make sure PostgreSQL is running and your app starts cleanly before you begin.

Each part ends the same way: you test through the API, then open the table in DBeaver and check the database agrees with what you saw.

---

## Part 1: Queries

### Task 1 — Derived Query

Add a derived query to `CustomerRepository` that finds all customers whose first name **contains** a given piece of text. A search for `an` should return every customer with `an` anywhere in their first name.

Wire it through the service interface, the service implementation, and a new endpoint in `CustomerController`.

Test it with a partial string, for example:

```
http://localhost:8080/customers/search/name?firstName=an
```

> **Hint:** The method name follows the same pattern as `findByFirstName`, with one keyword added to the end.

### Task 2 — JPQL Query

Add a second repository method, this time using `@Query` with JPQL. It should find all customers by **last name**, using a `WHERE` clause.

Wire it through the layers and add an endpoint.

Test it:

```
http://localhost:8080/customers/search/lastname?lastName=Tan
```

### Check in DBeaver

Open the `customer` table in DBeaver and view its data. Confirm that the rows returned by each endpoint are exactly the rows sitting in the table.

---

## Part 2: Validation

Add a new constraint to the `Customer` class so that `contactNo` must be **exactly 8 digits**, numbers only. Letters, symbols and spaces should be rejected.

Give it a clear message, for example `"Contact number must be exactly 8 digits"`.

> **Hint:** `@Size` only checks length, so it will not reject letters. Use `@Pattern` with a regular expression instead. A digit is written as `\\d` in a Java string.

### Test it

1. Send a `POST` to `/customers` with a contact number like `9123abcd`. You should get a `400 Bad Request` with your message.
2. Open the `customer` table in DBeaver and refresh it. The invalid customer should **not** be there.
3. Send another `POST` with a valid 8 digit contact number. You should get `201 Created`.
4. Refresh the table in DBeaver again. The new row should now appear.

---

## Part 3: Custom Exception

Right now, requesting `/customers/-5` goes all the way to the database looking for a customer with id `-5`. A negative id is never valid, so we should reject it before it gets that far.

### Task

1. Create a new exception class `InvalidCustomerIdException` in your `exceptions` folder. It should extend `RuntimeException` and take a `Long id`.
2. In `CustomerServiceImpl`, throw it from `getCustomer` when the id is zero or negative.
3. Add a handler for it in `GlobalExceptionHandler` that returns `400 BAD REQUEST` using the same `ErrorResponse` shape as your other handlers.

### Test it

1. `GET /customers/-5` should return `400` with your message.
2. `GET /customers/9999` should still return `404` with the customer-not-found message. Both handlers must work independently.

### Check in DBeaver

Open the `customer` table and look at the `id` column. Pick an id that exists and request it — you get the customer back. Request an id that is not in the table and you get the `404`. The table is the reason for the difference.

---

## Presenting

Three of you will present, one part each. Walk through the code you added, run the test, and then show the table in DBeaver.
