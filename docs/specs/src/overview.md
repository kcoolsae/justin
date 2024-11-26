Overview
===

See also:

* [Home page](index.md)

---

In order to organise the Bebras International Workshop, a lot of information is needed about the participants,
concerning their background as a member of the community and there preferences for (accommodation during) the workshop.
Traditionally, this information is gathered through several web-based forms (Google forms, say) that are filled in by
the participants in the weeks before the workshop.

This web application aims to streamline this process by

* using the same system from one year to the next,
* allowing data from earlier years to be reused,
* providing a single access point for questions related to tasks, accommodation, personal details, etc.

The data will be stored in a database, which can be consulted online or from which subsets can be extracted in the form
of Excel worksheets, by certain authorised users. Data remains stored over the years, so that participants need not fill
in the same information every year.

Data
----

The data stored falls into three different categories

**Personal data** Full name, email address, country, role. This information is used to identify the
participant. (A participant can see who else of their country is participating in the workshop and in what role.)

**Background** as a member of the community. This data is used by the task handling committee (THC) to set up work
groups.

**Preferences** Accommodation, dietary requirements, etc. This information is used by the host country to make
arrangements for the international workshop.

Important in this division is who has access to this data, the THC, the host, or both.

Note that the system treats the last two categories in a very similar way, so it is perfectly feasible to add
a category in the future without a rewrite.

Privileges
---
Users can be granted certain privileges which allows them to perform certain actions. Privileges are granted
only for the running year and can be restricted to one data category.

**Basic** Can see/edit their own data. Can see who of their country is participating in the workshop. Essentially
everybody who is registered with the system has this privilege.

**View data** Can view/download the data for a *specific category*. Typically, all THC members
will be given this privilege for the category 'background' and workshop organisers from the host
country will be given this privilege for the category 'preferences'. (It is possible to grant this
privilige to a user, e.g., a board member, for more than one category.)

**Edit forms** Can edit the forms used to request information. Typically, one or two members of the THC
will be given this privilege for the category 'background' while one or two works shop organisers will be
given this privilege for the category 'preferences'.

**View personal data** Can see names, email addresses and countries for *all* users (and not only
for users of the same country).

**Admin** Can assign/revoke privileges to/from users. Can reset the system between subsequent workshops.

{:.note}
Do we allow users with a given privilege to grant that privilege to others? E.g., can one work shop organiser grant the
rol of 'view host data' to
another person? Should we split the admin privilege into smaller parts?

Note that users must indicate each year whether they will participate in the task editing process (if not, they are
classified as *accompanying person*) and whether
they need accommodation. The latter will be the case for most participants, but as we keep a history of previous years,
the fact that a user is registered
with the system does not necessarily imply that they will participate in the workshop.

Authentication
---
As the web application will be used only a few times per year we would prefer not to use a password authentication
system, as most people
tend to forget passwords for this type of application and need to click the 'forgot password' link anyway. So we cut out
the middle man:

To authenticate, people have to type in their e-mail address and click a submit button. This sends a mail to that email
address with a
link they can use to access the system. This link is valid for a certain amount of time (say, 24 hours) and can be used
only once.

Registering as a new user is done in the same way, although we provide a separate 'Register' button for that purpose.
When registering, the user
must provide their full name and indicate the country they are associated with.

Users that belong to the same country can see each other's email address, name and whether they are participating in the
workshop that year. Currently,
this is the only check we have in place to prevent people from doing 'fake' registrations.

{:.note}
Should we provide an alternative way of authentication for users that need frequent access (administrators) or for whom
emails from the system
tend to arrive in their spam folder?

Language
--------
Only an English language user interface is provided.

{:.note}
This is important for technical reasons - internationalizing a web application which originally was not intended to be, is
an enormous amount of work. On the other hand, a single language application is much easier to program.


