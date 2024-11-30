Form pages
===
See also:
* [Home page](index.md)

---
The form structure is intended to mimic that used in popular applications such as 
[Google forms](google-forms.md). A [mockup](mockups/formpage1.html) of what the forms could look like is available.

The forms in which the data is entered, are organised hierarchically
into *categories*, *pages* (= *sections*) and *questions*.

Categories and pages can have titles and a header text. Questions
can be of different type

* Multiple choice (radio buttons / or drop down menu / or buttons?)
* Multiple choice with 'other' option (radio buttons + text field)
* Multiple response (check boxes)
* Multiple response with 'other'
* Free text (text field / text area).
* Date (special date field)

{:.note}
In addition, multiple choice *buttons* could be used, e.g., for yes/no questions (not in Google forms),
Questions could also be interspersed with explanatory text blocks (cf. Google forms).

Answers to questions can be pre-filled, from 
* last year's information
* default values (e.g., dates of arrival and departure)
* information taken from other questions (or from private data), e.g.,
  *name on credit card* could be pre-filled with *name of user*.

Pages group questions so that a user does not see one long page full of questions. Pages can have
explanatory information in the header and could temporarily be left blank by the user. *Go to next/previous page*
buttons are provided.

Questions are identified
by a structured alphanumeric identifier, consisting of /year/category/question,
e.g., `/2025/thc/nr-workshops-attended`, or simply `nr-workshops-attended`
when year and category are clear. This can be used when selecting
answers to be viewed/downloaded, or for internal references, e.g., to
indicate how one question could be pre-filled with the answer to
another question.

{:.note} Not sure whether pages should be part of this hierarchy. Google uses 'page dividers' on the same level
as questions.

Multiple choice or multiple response answers can be identified
in a similar way, e.g., `/2025/host/dietary-needs/vegetarian`.

{:.note}
Do we need conditional questions or pages, i.e., items that are
only shown when a certain answer is given to a question? The identifiers
described above, may help to easily specify such conditions. (Google
forms allow this partially!)

{:.note}
Note that category selection (*are you an accompanying person* or *do you intend
to participate in the task editing process*) is already a type of
conditional question.

Question text
---

Question text can be formatted using markdown. This allows for
italics, bold, lists, links to external pages, etc.

{:.note}
Do we need to support inclusion of images in the question text? This
is technically more difficult and requires an additional
user interface for the user to upload images.

Technical
---
It is possible to export a Google form so that it can be imported into the system or database. This requires some manual
work however, and most unfortunately, all formatting information (bold/italic/bullet lists/...) is lost in the process.

{:.note}
Programming the pages where users fill in data, is fairly straightforward. The part of the application that allows privileged users
to create and edit forms, is much more complex. In an early stage of the project it might be necessary to create forms
in a structured text file (xml, json, yaml) and import them into the system.

