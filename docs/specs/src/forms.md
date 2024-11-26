Form pages
===
See also:
* [Home page](index.md)

---
The forms in which the data is entered, are organised hierarchically
into *categories*, *pages* and *questions*.

Categories and pages can have titles and a header text. Questions
can be of different type

* Multiple choice (radio buttons)
* Multiple choice with 'other' option (radio buttons + text field)
* Multiple response (check boxes)
* Multiple response with 'other'
* Free text (text field / text area)

{:.note}
**TODO** Provide examples from questions used in previous years and make sure that these at least
are supported by the system.

A question may be optional or an answer may be required.

Answers to questions can be pre-filled, from 
* last year's information
* information taken from other questions (or from private data), e.g.,
  *name on credit card* could be pre-filled with *name of user*.

Pages simply group questions so that a user does not see one long page full of questions. Pages can have
explanatory information in the header and could temporarily be left blank by the user. *Go to next/previous page*
buttons are provided.

Questions are identified
by a structured alphanumeric identifier, consisting of /year/category/question,
e.g., `/2025/thc/nr-workshops-attended`, or simply `nr-workshops-attended`
when year and category are clear. This can be used when selecting
answers to be viewed/downloaded, or for internal references, e.g., to
indicate how one question could be pre-filled with the answer to
another question.

Multiple choice or multiple response answers can be identified
in a similar way, e.g., `/2025/host/dietary-needs/vegetarian`.

{:.note}
Do we need conditional questions or pages, i.e., items that are
only shown when a certain answer is given to a question? The identifiers
described above, may help to easily specify such conditions.

{:.note}
Note that category selection (*are you an accompanying person* or *do you intend
to participate in the task editing process*) is already a type of
conditional question.

Technical
---

{:.note}
It may be possible to export a Google form to a format that can be imported into the system or database.
(According to ChatGPT - haven't tried this, but worth pursuing.) It might be a good idea to use Google form capabilities
(types of questions, options, …) as a basis for the form system of this project.


