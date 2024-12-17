Form pages
===
See also:

* [Home page](index.md)

---
The form structure mimics that used in popular applications such as *Google Forms*.
A [mockup](mockups/formpage1.html) of what the forms could look like is available. The
[demo application](https://justin.ugent.be/justin) already supports all types listed below.

The forms in which the data is entered, are organised hierarchically
into *categories*, *pages* and *questions*.

Categories can have titles and a header text which is shown on each of the corresponding form.
Pages carry no additional data and are simply used to group questions. Questions
can be of different type

* Radio buttons (multiple choice)
* Drop-down list (multiple choice)
* Toggle buttons (multiple choice)
* Radio buttons with 'other' option (multiple choice). When selecting the last option (Other…) a text field appears.
* Check boxes (multiple response)
* Check boxes with 'other' (multiple response). When selecting the last option (Other…) a text field appears. Users
  are allowed only a single 'other' answer.
* Text field (one line of text).
* Text area (multiple lines of text).
* Date (special date field)

Next to this also explanatory text blocks can be added to the form. Typically, these are used at the top of a new page,
but they
are also allowed to be interspersed between questions.

Answers to questions can be pre-filled, from

* a form from an earlier event (e.g., last year's form of the same category)
* default values (e.g., dates of arrival and departure)
* information taken from private data, e.g., *name on credit card* could be pre-filled with *name of user*.
* information from another form of the same event, This is less useful as pre-filling happens the first time a form is
  accessed (see below)

## User interface

Questions are grouped into pages. Each page display a general header, the same for all pages of the same category and
below that
the questions for that page are listed one after another, in a fixed order.

At the bottom of the page,
a 'Submit'-button is provided to register the answers given on that page. (The user must indeed *actively* submit their
answers, just clicking
on a radio button is not sufficient.) Pressing this button saves the answers and moves the user to the next page, or to
the user's starting page
if the last page of the form has been reached.

Each page also provides (at the top) a 'back to main page', a 'skip to next page' and a 'skip to previous page'
link (when appropriate). These are navigation links only: no data is saved when using them.

The first time a user accesses a form, the form is pre-filled with data from the user's profile and earlier forms, as
described above.
A separate warning page is shown informing the user about the fact that pages have to be submitted to be registered and
that some
data may already be pre-filled by the system.

## Identifiers

Questions are identified
by a structured alphanumeric identifier, consisting of year/category/question,
e.g., `2025/thc/nr-workshops-attended`. This can be used when selecting
answers to be viewed/downloaded, or for internal references, e.g., to
indicate how one question could be pre-filled with the answer to
another question. (Pages are not included in this identifier.)

Multiple choice or multiple response options can be identified
in a similar way, e.g., `2025/host/dietary-needs/vegetarian`.

In some contexts abbreviations and special notations can be used. `2024/` could indicate 'the same category and
question as this one, but in the `2024` event'. `~full-name` could indicate 'the full name of the user' as taken from the
profile. 

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
Programming the pages where users fill in data, is fairly straightforward (and is already implemented at the current time). The part of the application that allows
privileged users to create and edit forms, is much more complex - and of low priority. For 2025 the forms will be imported directly into the database
from an XML file.

